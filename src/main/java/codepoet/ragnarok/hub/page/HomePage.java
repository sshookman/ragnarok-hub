package codepoet.ragnarok.hub.page;

import codepoet.ragnarok.annotation.Page;
import codepoet.ragnarok.hub.PageData;
import codepoet.ragnarok.hub.Pageable;
import codepoet.ragnarok.hub.Route;
import codepoet.venalartificer.TemplateBuilder;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Page(name = "home")
public class HomePage implements Pageable {

	private final Map<String, Object> templateData;
	private final TemplateBuilder templateBuilder;
	private final Map<String, Route> routes;

	@Autowired
	public HomePage(TemplateBuilder templateBuilder) {
		this.templateBuilder = templateBuilder;
		this.templateData = new HashMap<>();

		this.routes = new HashMap<>();
		this.routes.put("a", new Route.Builder("archives").build());
		this.routes.put("b", new Route.Builder("bookmarks").build());
		this.routes.put("s", new Route.Builder("settings").build());
		this.routes.put("h", new Route.Builder("help").build());
		this.routes.put("e", null);
	}

	@Override
	public PageData render(Map<String, String> params, String input) {
		String updates = null;

		templateData.put("hasUpdates", updates != null);
		templateData.put("updates", updates);
		String renderText = templateBuilder.render("Home", templateData);
		PageData.Builder pageData = new PageData.Builder(renderText, "");

		routes.entrySet().stream().forEach((route) -> {
			pageData.route(route.getKey(), route.getValue());
		});

		return pageData.build();
	}
}
