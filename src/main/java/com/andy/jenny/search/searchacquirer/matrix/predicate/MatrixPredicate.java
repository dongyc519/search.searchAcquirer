package com.andy.jenny.search.searchacquirer.matrix.predicate;

import java.util.function.Predicate;

import com.andy.jenny.search.searchacquirer.matrix.pojo.MatrixObject;

public class MatrixPredicate implements Predicate<MatrixObject> {

	Object key;

	public MatrixPredicate(Object key) {
		this.key = key;

	}

	@Override
	public boolean test(MatrixObject t) {
		return t.getCode().equals(key);
	}
}
