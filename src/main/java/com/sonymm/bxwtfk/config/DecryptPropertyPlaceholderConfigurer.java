package com.sonymm.bxwtfk.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.sonymm.bxwtfk.util.DesUtils;

public class DecryptPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	private String[] propertyNames;

	public void setPropertyNames(String[] propertyNames) {
		this.propertyNames = propertyNames;
	}

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if (isEncryptPropertyVal(propertyName)) {
			return DesUtils.getDecryptString(propertyValue);
		} else {
			return propertyValue;
		}
	}

	public boolean isEncryptPropertyVal(String propertyName) {
		boolean flag = false;
		for (String property : propertyNames) {
			if (propertyName.equals(property)) {
				flag = true;
				break;
			}
		}
		return flag;
	}
}
