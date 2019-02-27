package com.andy.jenny.search.searchacquirer.convert;

import java.util.Collection;

public interface Converter<T> {
	
	/**
	 * 对象转换
	 * @param t
	 */
	void convert(T t);
	
	/**
	 * 对象列表转换
	 * @param collection
	 */
	void convert(Collection<T> collection);
}
