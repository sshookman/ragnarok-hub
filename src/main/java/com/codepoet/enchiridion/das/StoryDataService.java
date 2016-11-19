package com.codepoet.enchiridion.das;

import com.codepoet.enchiridion.das.model.StoryDO;
import java.sql.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoryDataService extends AbstractDataService<StoryDO> {

	@Autowired
	public StoryDataService(final Connection hubDatabaseConnection) {
		super(new StoryDO(), hubDatabaseConnection);
	}
}
