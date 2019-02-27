package com.andy.jenny.search.searchacquirer.acquirer.sort;

import lombok.Getter;

public enum SortKey {
	ORDERBYTIME("time"), ORDERBYID("id");
	
	@Getter
	private String key;
	
	SortKey(String key){
		this.key = key;
	}
}
