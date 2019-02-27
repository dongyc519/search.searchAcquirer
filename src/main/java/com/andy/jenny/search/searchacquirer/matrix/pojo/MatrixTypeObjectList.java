package com.andy.jenny.search.searchacquirer.matrix.pojo;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.constraints.NotBlank;

import com.andy.jenny.search.searchacquirer.matrix.predicate.MatrixTypePredicate;

public class MatrixTypeObjectList extends ArrayList<MatrixTypeObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MatrixTypeObject get(@NotBlank String title) {
		MatrixTypePredicate predicate = new MatrixTypePredicate(title);
		Optional<MatrixTypeObject> firstOptional = this.parallelStream().filter(predicate).findFirst();
		if (firstOptional.isPresent()) {
			return firstOptional.get();
		}
		return null;
	}
}
