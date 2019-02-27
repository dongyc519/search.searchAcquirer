package com.andy.jenny.search.searchacquirer.matrix.pojo;

import java.util.ArrayList;
import java.util.Optional;
import javax.validation.constraints.NotNull;

import com.andy.jenny.search.searchacquirer.matrix.predicate.MatrixPredicate;

public class MatrixObjectList extends ArrayList<MatrixObject> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static final MatrixObject DefaultObject = new MatrixObject();

	static {
		DefaultObject.setCode("99");
		DefaultObject.setName("其他");
	}

	public MatrixObject get(@NotNull Object code) {
		MatrixPredicate predicate = new MatrixPredicate(code);
		Optional<MatrixObject> firstOne = this.parallelStream().filter(predicate).findFirst();

		MatrixObject result = DefaultObject;
		if (firstOne.isPresent()) {
			result = firstOne.get();
		}
		return result;
	}
}
