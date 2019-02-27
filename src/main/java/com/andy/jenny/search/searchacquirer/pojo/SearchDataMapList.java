package com.andy.jenny.search.searchacquirer.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.andy.jenny.search.searchacquirer.acquirer.repeat.RepeatConsumer;
import com.andy.jenny.search.searchacquirer.acquirer.repeat.RepeatKey;
import com.andy.jenny.search.searchacquirer.acquirer.sort.SortComparator;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 查询结果集对象
 * 
 * @author dyc
 * @version 创建时间：2019-01-28 2:08:35 PM
 * @param <T>
 *            结果集成员
 */
@Slf4j
public class SearchDataMapList<T> extends ArrayList<T> implements CommonSearchExcute<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int defaultListFixSize = 2000;

	static String pRowsName = "rows";
	static int defaultPageRows = 12;

	private Properties defaultProperties;
	private boolean isSetProperty = false;

	public boolean isSetProperty() {
		return isSetProperty;
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public void setPropertys(String name, String value) {
		if (defaultProperties == null) {
			defaultProperties = new Properties();
		}
		defaultProperties.setProperty(name, value);
		isSetProperty = true;
	}

	public SearchDataMapList() {
		super(defaultListFixSize);
	}

	public SearchDataMapList(Collection<T> datas) {
		super(datas);
	}

	@SafeVarargs
	public SearchDataMapList(T... datas) {
		super(Lists.newArrayList(datas));
	}

	@Getter
	private long total;

	public synchronized void addTotal(long currentTotal) {
		total += currentTotal;
	}

	@Getter
	@Setter
	private double takeTime;

	@Override
	public void repeatRecords() {
		Map<String, T> tempMap = Maps.newHashMap();
		RepeatConsumer<T> repeatConsumer = new RepeatConsumer<>(RepeatKey.URLANDTIME, tempMap);
		this.forEach(repeatConsumer);
		this.clear();
		this.addAll(tempMap.values());
	}

	@Override
	public void sortRecords() {
		SortComparator<T> sortComparator = new SortComparator<>();
		try {
			this.sort(sortComparator);
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		}
	}

	@Override
	public List<List<T>> partitionRecords() {
		int rows = defaultPageRows;
		if (isSetProperty) {
			rows = Integer.valueOf(defaultProperties.getOrDefault("rows", defaultPageRows).toString());
		}
		SearchPageExcute<T> searchPageExcute = new SearchPageExcute<>(rows, this);
		return searchPageExcute.partition();
	}

	private class SearchPageExcute<TK> {
		int rows;
		List<TK> datas;

		SearchPageExcute(int limits, List<TK> lists) {
			rows = limits;
			datas = lists;
		}

		public List<List<TK>> partition() {
			return Lists.partition(datas, rows);
		}
	}

	@Override
	public T getFirst() {
		return this.get(0);
	}
}
