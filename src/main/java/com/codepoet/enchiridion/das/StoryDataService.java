package com.codepoet.enchiridion.das;

import com.codepoet.enchiridion.das.model.StoryDO;
import java.sql.Connection;
import org.springframework.stereotype.Component;

@Component
public class StoryDataService extends AbstractDataService<StoryDO> {

	public StoryDataService(final Connection story) {
		super(new StoryDO(), story);
	}
}
