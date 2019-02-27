package com.andy.jenny.search.searchacquirer.callable;

import java.util.Map;

import com.andy.jenny.search.searchacquirer.core.SearchMapper;

public abstract class AbstractSearchDetailsCallable<T> extends AbstractSearchCallable<T> {

	public AbstractSearchDetailsCallable(Map<?, ?> params, SearchMapper searchMapper) {
		super(params, searchMapper);
	}

}
