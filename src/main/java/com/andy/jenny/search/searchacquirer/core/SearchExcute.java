package com.andy.jenny.search.searchacquirer.core;

import com.andy.jenny.search.searchacquirer.pojo.SearchDataMapList;

public interface SearchExcute<T> extends DisabledObject {	
	
	/**
	 * 查询操作
	 * @return 返回结果集合
	 * @throws Exception
	 */
	SearchDataMapList<T> excute() throws Exception;
}
