package com.andy.jenny.search.searchacquirer.matrix;

import com.andy.jenny.search.searchacquirer.cache.ICache;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultStandardMatrix extends AbstractStandardMatrix {

	public DefaultStandardMatrix(ICache cache) {
		super(cache);
	}

	@Override
	protected void initCacheData() {
		log.info("default matrix do not initCacheData.");
	}

	@Override
	protected Object stampingPlateType(Object value) {
		return value;
	}

	@Override
	protected Object stampingPlateColor(Object value) {
		return value;
	}

	@Override
	protected Object stampingVehicleType(Object value) {
		return value;
	}

	@Override
	protected Object stampingVehicleColor(Object value) {
		return value;
	}

	@Override
	protected Object stampingBrand(Object value) {
		return value;
	}

	@Override
	protected Object stampingDirection(Object value) {
		return value;
	}

	@Override
	protected Object stampingCalling(Object value) {
		return value;
	}

	@Override
	protected Object stampingSafe(Object value) {
		return value;
	}

}
