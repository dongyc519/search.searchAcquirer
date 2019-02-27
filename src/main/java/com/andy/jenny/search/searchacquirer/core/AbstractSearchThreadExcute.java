package com.andy.jenny.search.searchacquirer.core;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

import com.andy.jenny.search.searchacquirer.callable.AbstractSearchCallable;
import com.andy.jenny.search.searchacquirer.pojo.SearchDataMap;
import com.andy.jenny.search.searchacquirer.pojo.SearchDataMapList;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractSearchThreadExcute<T> implements SearchExcute<T> {

	private static final int DEFAULT_THREADCOUNT = 2;

	protected Set<SearchMapper> searchMappers;
	ExecutorService service;
	protected Map<?, ?> params;

	public AbstractSearchThreadExcute(int poolSize, Map<?, ?> params, SearchMapper... s) {
		Preconditions.checkArgument(poolSize != 0, "poolSize is not allow 0.");
		this.params = params;
		searchMappers = Sets.newHashSet();
		service = Executors.newFixedThreadPool(poolSize);
		searchMappers.addAll(Lists.newArrayList(s));
	}

	public AbstractSearchThreadExcute(Map<?, ?> params, SearchMapper... s) {
		this(DEFAULT_THREADCOUNT, params, s);
	}

	@Override
	public SearchDataMapList<T> excute() throws Exception {
		Long t1 = System.currentTimeMillis();

		SearchDataMapList<T> searchDataMapList = createSearchDataMapList();
		List<AbstractSearchCallable<T>> callables = creatCalls();
		SearchResultConsumer<T> searchResultConsumer = new SearchResultConsumer<>(searchDataMapList);
		service.invokeAll(callables).forEach(searchResultConsumer);
		Long t2 = System.currentTimeMillis();

		searchDataMapList.setTakeTime(((double) t2 - t1) / 1000);
		
		return searchDataMapList;
	}

	protected abstract SearchDataMapList<T> createSearchDataMapList();

	protected abstract List<AbstractSearchCallable<T>> creatCalls();

	@Override
	public void disable() {
		if (service != null && !service.isShutdown()) {
			service.shutdownNow();
			service = null;
		}
		if (searchMappers != null && !searchMappers.isEmpty()) {
			searchMappers.clear();
			searchMappers = null;
		}
	}

	private class SearchResultConsumer<TK extends T> implements Consumer<Future<SearchDataMap<TK>>> {

		private SearchDataMapList<TK> maps;

		SearchResultConsumer(SearchDataMapList<TK> dataMaps) {
			maps = dataMaps;
		}

		@Override
		public void accept(Future<SearchDataMap<TK>> future) {
			try {
				if (future.isDone() || future.isCancelled()) {
					maps.addAll(future.get().getSearchData());
					maps.addTotal(future.get().getCurrentTotal());
				}
			} catch (Exception e) {
				log.error("get searchResult error.", e);
			}
		}

	}

}
