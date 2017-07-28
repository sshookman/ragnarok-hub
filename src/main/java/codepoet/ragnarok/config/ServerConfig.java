package codepoet.ragnarok.config;

import codepoet.venalartificer.TemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfig {

	@Bean
	public TemplateBuilder templateBuilder() throws Exception {
		return new TemplateBuilder("templates");
	}
}
