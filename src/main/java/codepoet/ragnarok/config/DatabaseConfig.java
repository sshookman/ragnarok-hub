package codepoet.ragnarok.config;

import codepoet.ragnarok.das.ConnectionManager;
import codepoet.ragnarok.das.DataService;
import codepoet.ragnarok.das.model.AuthorDO;
import codepoet.ragnarok.das.model.ConfigDO;
import codepoet.ragnarok.das.model.PlayerDO;
import codepoet.ragnarok.das.model.StoryDO;
import java.sql.Connection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	@Value("${database.hub}")
	private String hubDatabase;

	@Bean
	public Connection hubDatabaseConnection() throws Exception {
		return ConnectionManager.establishConnection(hubDatabase);
	}

	@Bean
	public DataService<StoryDO> storyDataService() throws Exception {
		return new DataService<StoryDO>(new StoryDO(), hubDatabaseConnection());
	}

	@Bean
	public DataService<PlayerDO> playerDataService() throws Exception {
		return new DataService<PlayerDO>(new PlayerDO(), hubDatabaseConnection());
	}

	@Bean
	public DataService<AuthorDO> authorDataService() throws Exception {
		return new DataService<AuthorDO>(new AuthorDO(), hubDatabaseConnection());
	}

	@Bean
	public DataService<ConfigDO> configDataService() throws Exception {
		return new DataService<ConfigDO>(new ConfigDO(), hubDatabaseConnection());
	}
}
