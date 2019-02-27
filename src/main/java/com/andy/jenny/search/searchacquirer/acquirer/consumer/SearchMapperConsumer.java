package com.andy.jenny.search.searchacquirer.acquirer.consumer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.andy.jenny.search.searchacquirer.callable.AbstractSearchCallable;
import com.andy.jenny.search.searchacquirer.core.SearchMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SearchMapperConsumer<C extends AbstractSearchCallable<T>, T> implements Consumer<SearchMapper> {

	private List<AbstractSearchCallable<T>> vehicleCallablelist;
	private Map<?, ?> params;
	private Class<C> clazz;

	public SearchMapperConsumer(List<AbstractSearchCallable<T>> list, Map<?, ?> params, Class<C> clazz) {
		vehicleCallablelist = list;
		this.params = params;
		this.clazz = clazz;
	}

	@Override
	public void accept(SearchMapper t) {
		try {
			vehicleCallablelist.add(callableFindVehicle(params, t));
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			log.error(e.getMessage(), e);
		}
	}

	private C callableFindVehicle(Map<?, ?> params, SearchMapper searchMapper)
			throws InstantiationException, IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Constructor<C> constructor = clazz.getConstructor(Map.class, SearchMapper.class);
		return constructor.newInstance(params, searchMapper);
	}
}
