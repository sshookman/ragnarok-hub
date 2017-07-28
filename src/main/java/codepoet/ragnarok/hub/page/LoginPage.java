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
@Page(name = "login")
public class LoginPage implements Pageable {

	private Map<String, Object> templateData;
	private TemplateBuilder templateBuilder;

	@Autowired
	public LoginPage(TemplateBuilder templateBuilder) {
		this.templateBuilder = templateBuilder;
		this.templateData = new HashMap<>();
	}

	@Override
	public PageData render(Map<String, String> params, String username) {
		Boolean isRegistered = true; //TODO: Lookup in database

		templateData.put("isRegistered", isRegistered);
		String renderText = templateBuilder.render("Login", templateData);
		Route route = new Route.Builder("home")
				.param("username", username)
				.param("isRegistered", isRegistered.toString())
				.build();

		return new PageData.Builder(renderText, "Please enter your password")
				.route("*", route)
				.build();
	}
}
