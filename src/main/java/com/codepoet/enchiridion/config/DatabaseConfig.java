package com.codepoet.enchiridion.config;

import com.codepoet.enchiridion.das.ConnectionManager;
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
}
