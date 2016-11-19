package com.codepoet.enchiridion.config;

import com.codepoet.enchiridion.das.ConnectionManager;
import java.sql.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

	@Bean
	public Connection hubDatabaseConnection() throws Exception {
		return ConnectionManager.establishConnection("srv/hub.sqlite");
	}
}
