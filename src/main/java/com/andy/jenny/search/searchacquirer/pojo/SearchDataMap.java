package com.andy.jenny.search.searchacquirer.pojo;

import java.util.HashMap;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public abstract class SearchDataMap<T> extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	protected long total;

	@Getter
	@Setter
	protected long currentTotal;

	@Getter
	@Setter
	protected List<T> searchData;

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
}
