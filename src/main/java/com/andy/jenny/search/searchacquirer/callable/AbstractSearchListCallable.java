package com.andy.jenny.search.searchacquirer.callable;

import java.util.Map;

import com.andy.jenny.search.searchacquirer.core.SearchMapper;

public abstract class AbstractSearchListCallable<T> extends AbstractSearchCallable<T> {

	public AbstractSearchListCallable(Map<?, ?> params, SearchMapper searchMapper) {
		super(params, searchMapper);
	}
	
	protected abstract int listRecordsNum();

	protected abstract int recordsNum();

}
