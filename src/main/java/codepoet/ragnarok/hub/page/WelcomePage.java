package codepoet.ragnarok.hub.page;

import codepoet.ragnarok.annotation.Page;
import codepoet.ragnarok.hub.PageData;
import codepoet.ragnarok.hub.Pageable;
import codepoet.ragnarok.hub.Route;
import codepoet.venalartificer.TemplateBuilder;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Page(name = "welcome")
public class WelcomePage implements Pageable {

	private TemplateBuilder templateBuilder;

	@Autowired
	public WelcomePage(TemplateBuilder templateBuilder) {
		this.templateBuilder = templateBuilder;
	}

	@Override
	public PageData render(Map<String, String> params, String input) {
		String renderText = templateBuilder.render("Welcome", null);
		return new PageData.Builder(renderText, "Please enter your username")
				.route("*", new Route.Builder("login").build())
				.build();
	}
}
