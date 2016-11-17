package com.codepoet.enchiridion.das;

import com.codepoet.enchiridion.das.model.ConfigDO;
import java.sql.Connection;

public class ConfigDataService extends AbstractDataService<ConfigDO> {

	public ConfigDataService(final Connection story) {
		super(new ConfigDO(), story);
	}
}
