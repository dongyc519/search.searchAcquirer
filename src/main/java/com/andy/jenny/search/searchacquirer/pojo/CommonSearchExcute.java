package com.andy.jenny.search.searchacquirer.pojo;

import java.util.List;

public interface CommonSearchExcute<T> {

	void repeatRecords();

	void sortRecords();

	List<List<T>> partitionRecords() throws Exception;
	
	T getFirst();
}
