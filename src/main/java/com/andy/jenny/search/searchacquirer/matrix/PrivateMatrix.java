package com.andy.jenny.search.searchacquirer.matrix;

import java.util.NoSuchElementException;

import com.andy.jenny.search.searchacquirer.cache.ICache;
import com.andy.jenny.search.searchacquirer.enums.MatrixType;

public class PrivateMatrix extends AbstractStandardMatrix {

	public PrivateMatrix(ICache cache) {
		super(cache);
	}

	/**
	 * 使用1400标准
	 */
	static final MatrixType matrixString = MatrixType.GB1400;

	@Override
	protected Object stampingPlateType(Object value) {
		try {
			return allDicTypeObjects.get(MatrixConstant.PLATETYPEDESC).getObjectList().get(value).getName();
		} catch (NoSuchElementException e) {
			return MatrixConstant.UNKNOW;
		}

	}

	@Override
	protected Object stampingPlateColor(Object value) {
		try {
			return allDicTypeObjects.get(MatrixConstant.PLATECOLORDESC).getObjectList().get(value).getName();
		} catch (NoSuchElementException e) {
			return MatrixConstant.UNKNOW;
		}

	}

	@Override
	protected Object stampingVehicleType(Object value) {
		try {
			return allDicTypeObjects.get(MatrixConstant.VEHICLETYPEDESC).getObjectList().get(value).getName();
		} catch (NoSuchElementException e) {
			return MatrixConstant.UNKNOW;
		}
	}

	@Override
	protected Object stampingVehicleColor(Object value) {
		try {
			return allDicTypeObjects.get(MatrixConstant.VEHICLECOLORDESC).getObjectList().get(value).getName();
		} catch (NoSuchElementException e) {
			return MatrixConstant.UNKNOW;
		}
	}

	@Override
	protected Object stampingBrand(Object value) {
		try {
			return allDicTypeObjects.get(MatrixConstant.BRANDDESC).getObjectList().get(value).getName();
		} catch (NoSuchElementException e) {
			return MatrixConstant.UNKNOW;
		}
	}

	@Override
	protected Object stampingDirection(Object value) {
		try {
			return allDicTypeObjects.get(MatrixConstant.DICDESC).getObjectList().get(value).getName();
		} catch (NoSuchElementException e) {
			return MatrixConstant.UNKNOW;
		}
	}

	@Override
	protected Object stampingCalling(Object value) {
		try {
			return allDicTypeObjects.get(MatrixConstant.CALLINGDESC).getObjectList().get(value).getName();
		} catch (NoSuchElementException e) {
			return MatrixConstant.UNKNOW;
		}
	}

	@Override
	protected Object stampingSafe(Object value) {
		try {
			return allDicTypeObjects.get(MatrixConstant.SAFETYBELTDESC).getObjectList().get(value).getName();
		} catch (NoSuchElementException e) {
			return MatrixConstant.UNKNOW;
		}
	}

	@Override
	protected void initCacheData() {
		allDicTypeObjects = cache.findAllDictDataByDictType();
	}

}
