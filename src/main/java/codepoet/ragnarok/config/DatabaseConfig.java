package codepoet.ragnarok.config;

import codepoet.ragnarok.das.model.AuthorDO;
import codepoet.ragnarok.das.model.ConfigDO;
import codepoet.ragnarok.das.model.PlayerDO;
import codepoet.ragnarok.das.model.StoryDO;
import codepoet.vaultmonkey.service.SqliteDataService;
import codepoet.vaultmonkey.util.SqliteConnectionUtil;
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
		return SqliteConnectionUtil.establishConnection(hubDatabase);
	}

	@Bean
	public SqliteDataService<StoryDO> storyDataService() throws Exception {
		return new SqliteDataService<StoryDO>(StoryDO.class, hubDatabaseConnection());
	}

	@Bean
	public SqliteDataService<PlayerDO> playerDataService() throws Exception {
		return new SqliteDataService<PlayerDO>(PlayerDO.class, hubDatabaseConnection());
	}

	@Bean
	public SqliteDataService<AuthorDO> authorDataService() throws Exception {
		return new SqliteDataService<AuthorDO>(AuthorDO.class, hubDatabaseConnection());
	}

	@Bean
	public SqliteDataService<ConfigDO> configDataService() throws Exception {
		return new SqliteDataService<ConfigDO>(ConfigDO.class, hubDatabaseConnection());
	}
}
