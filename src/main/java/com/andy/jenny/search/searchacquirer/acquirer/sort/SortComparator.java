package com.andy.jenny.search.searchacquirer.acquirer.sort;

import java.sql.Timestamp;
import java.util.Comparator;

public class SortComparator<T> implements Comparator<T> {

	private static SortKey defaultSortKey = SortKey.ORDERBYTIME;

	private SortKey sortKey;

	public SortComparator(SortKey key) {
		sortKey = key;
	}

	public SortComparator() {
		sortKey = defaultSortKey;
	}

	@Override
	public int compare(T o1, T o2) {
		String tmpKey = sortKey.getKey();
		try {
			Timestamp time1 = (Timestamp) o1.getClass().getDeclaredField(tmpKey).get(o1);
			Timestamp time2 = (Timestamp) o2.getClass().getDeclaredField(tmpKey).get(o2);



			if (time1.equals(time2)) {
				return 1;
			}
			return time2.compareTo(time1);
		} catch (Exception e) {
			return 1;
		}
	}

}
