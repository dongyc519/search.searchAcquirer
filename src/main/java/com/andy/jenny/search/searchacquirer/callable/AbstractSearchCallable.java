package com.andy.jenny.search.searchacquirer.callable;

import java.util.Map;
import java.util.concurrent.Callable;

import com.andy.jenny.search.searchacquirer.core.SearchMapper;
import com.andy.jenny.search.searchacquirer.pojo.SearchDataMap;

public abstract class AbstractSearchCallable<T> implements Callable<SearchDataMap<T>> {

	public AbstractSearchCallable(Map<?, ?> params, SearchMapper searchMapper) {
		this.params = params;
		this.searchMapper = searchMapper;
	}

	protected Map<?, ?> params;
	protected SearchMapper searchMapper;
}
