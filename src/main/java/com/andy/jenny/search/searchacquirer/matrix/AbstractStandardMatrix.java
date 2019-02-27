package com.andy.jenny.search.searchacquirer.matrix;

import com.andy.jenny.search.searchacquirer.cache.ICache;
import com.andy.jenny.search.searchacquirer.enums.Matrix;
import com.andy.jenny.search.searchacquirer.matrix.pojo.MatrixTypeObjectList;

public abstract class AbstractStandardMatrix {

	protected ICache cache;

	public AbstractStandardMatrix(ICache cacheService) {
		this.cache = cacheService;
		initCacheData();
	}

	protected MatrixTypeObjectList allDicTypeObjects;

	public Object stamping(Matrix matrix, Object value) {
		switch (matrix) {
		case PLATECOLOR:
			return stampingPlateColor(value);
		case PLATETYPE:
			return stampingPlateType(value);
		case VEHICLECOLOR:
			return stampingVehicleColor(value);
		case VEHICLETYPE:
			return stampingVehicleType(value);
		case BRAND:
			return stampingBrand(value);
		case DIRECTION:
			return stampingDirection(value);
		default:
			break;
		}
		throw new IllegalArgumentException(String.format("Matrix:%s is not def.", matrix.name()));
	}

	protected abstract Object stampingCalling(Object value);

	protected abstract Object stampingSafe(Object value);

	protected abstract void initCacheData();

	protected abstract Object stampingPlateType(Object value);

	protected abstract Object stampingPlateColor(Object value);

	protected abstract Object stampingVehicleType(Object value);

	protected abstract Object stampingVehicleColor(Object value);

	protected abstract Object stampingBrand(Object value);

	protected abstract Object stampingDirection(Object value);
}
