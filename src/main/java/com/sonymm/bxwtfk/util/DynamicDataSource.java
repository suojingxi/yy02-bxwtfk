package com.sonymm.bxwtfk.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Service;

/**
 * @ClassName: DynamicDataSource
 * @Description: 多数据源
 * @author sjx
 * @date 2016年04月02日 早晨2:04:37
 */
@Service
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
	public Object determineCurrentLookupKey() {
		return DyContextHolder.getCustomerType();
    }

}
