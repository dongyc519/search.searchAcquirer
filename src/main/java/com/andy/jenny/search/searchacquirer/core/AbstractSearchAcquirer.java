package com.andy.jenny.search.searchacquirer.core;

import java.util.Map;

import com.andy.jenny.search.searchacquirer.cache.ICache;
import com.andy.jenny.search.searchacquirer.convert.Converter;
import com.andy.jenny.search.searchacquirer.enums.MatrixType;
import com.andy.jenny.search.searchacquirer.enums.SearchType;
import com.andy.jenny.search.searchacquirer.pojo.SearchDataMapList;

public abstract class AbstractSearchAcquirer<T, R> implements DisabledObject, SearchGetter<R> {

	protected ICache cache;
	protected Converter<T> converter;

	private SearchExcute<T> searchExcute;

	public AbstractSearchAcquirer(SearchType searchType, Map<?, ?> params, ICache cache,
			MatrixType matrixType, SearchMapper... s) {
		this.cache = cache;
		searchExcute = createExcuter(searchType, params, s);
		converter = createConverter(matrixType);
	}

	public AbstractSearchAcquirer(SearchType searchType, Map<?, ?> params, ICache cache,
			SearchMapper... s) {
		this(searchType, params, cache, MatrixType.GB1400, s);
	}

	/**
	 * 创建excuter
	 * @param searchType
	 * @param params
	 * @param s
	 * @return
	 */
	protected abstract SearchExcute<T> createExcuter(SearchType searchType, Map<?, ?> params,
			SearchMapper... s);

	/**
	 * 创建converter
	 * @param matrixType
	 * @return
	 */
	protected abstract Converter<T> createConverter(MatrixType matrixType);
	
	/**
	 * 结果格式化
	 * @param tList
	 * @return
	 */
	protected abstract R formatResult(SearchDataMapList<T> tList);
	
	protected SearchDataMapList<T> getFromAcquirer() throws Exception {
		SearchDataMapList<T> searchDataMapList = searchExcute.excute();
		if (converter != null) {
			converter.convert(searchDataMapList);
		}
		return searchDataMapList;
	}

	@Override
	public void disable() {
		searchExcute.disable();
	}
}
