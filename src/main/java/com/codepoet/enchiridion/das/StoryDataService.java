package com.codepoet.enchiridion.das;

import com.codepoet.enchiridion.das.model.StoryDO;
import java.sql.Connection;

public class StoryDataService extends AbstractDataService<StoryDO> {

	public StoryDataService(final Connection story) {
		super(new StoryDO(), story);
	}
}
