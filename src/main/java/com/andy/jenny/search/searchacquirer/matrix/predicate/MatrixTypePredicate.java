package com.andy.jenny.search.searchacquirer.matrix.predicate;

import java.util.function.Predicate;

import com.andy.jenny.search.searchacquirer.matrix.pojo.MatrixTypeObject;

public class MatrixTypePredicate implements Predicate<MatrixTypeObject> {

	String title;

	public MatrixTypePredicate(String title) {
		this.title = title;
	}

	@Override
	public boolean test(MatrixTypeObject t) {
		return t.getTitle().equals(title);
	}
}
