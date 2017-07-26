package codepoet.ragnarok.hub;

import codepoet.ragnarok.annotation.Page;
import codepoet.venalartificer.TemplateBuilder;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Page(name = "welcome")
public class WelcomePage implements Pageable {

	private Map<String, Object> templateData;
	private TemplateBuilder templateBuilder;

	@Autowired
	public WelcomePage(TemplateBuilder templateBuilder) {
		this.templateBuilder = templateBuilder;
	}

	@Override
	public PageData render(Map<String, String> params) {
		String renderText = templateBuilder.render("Welcome", templateData);
		return new PageData.Builder(renderText, "Please enter your username")
				.route("*", new Route.Builder("login").build())
				.build();
	}
}
