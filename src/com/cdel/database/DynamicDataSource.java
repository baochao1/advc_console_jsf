package com.cdel.database;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	@Override
	protected Object determineCurrentLookupKey() {
		if (DbContextHolder.getDbType() == null
				|| DbContextHolder.getDbType().equals("")) {
			DbContextHolder.setDbType("jiaowu");
		}
		return DbContextHolder.getDbType();
	}
}
