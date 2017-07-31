package codepoet.ragnarok.hub.page;

import codepoet.ragnarok.annotation.Page;
import codepoet.ragnarok.hub.PageData;
import codepoet.ragnarok.hub.Pageable;
import codepoet.ragnarok.hub.Route;
import codepoet.ragnarok.model.PlayerDO;
import codepoet.vaultmonkey.service.SqliteDataService;
import codepoet.venalartificer.TemplateBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Page(name = "login")
public class LoginPage implements Pageable {

	private Map<String, Object> templateData;
	private TemplateBuilder templateBuilder;
	private SqliteDataService<PlayerDO> playerDataService;

	@Autowired
	public LoginPage(TemplateBuilder templateBuilder, SqliteDataService playerDataService) {
		this.templateBuilder = templateBuilder;
		this.playerDataService = playerDataService;
		this.templateData = new HashMap<>();
	}

	@Override
	public PageData render(Map<String, String> params, String username) {
		//TODO: Search does not appear to be working properly
		Map<String, String> search = new HashMap<>();
		search.put("username", username);
		List<PlayerDO> players = playerDataService.read(search);
		Boolean isRegistered = !players.isEmpty();

		templateData.put("isRegistered", isRegistered);
		String renderText = templateBuilder.render("Login", templateData);
		Route route = new Route.Builder("home")
				.param("username", username)
				.param("isRegistered", isRegistered.toString())
				.build();

		return new PageData.Builder(renderText, "Please enter your password")
				.route(isRegistered ? players.get(0).getPassword() : "*", route)
				.build();
	}
}
