package codepoet.ragnarok.hub;

import codepoet.ragnarok.annotation.Page;
import codepoet.ragnarok.exception.HubException;
import codepoet.ragnarok.hub.page.HomePage;
import codepoet.ragnarok.model.PlayerDO;
import codepoet.vaultmonkey.service.SqliteDataService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageRouter {

	private final SqliteDataService<PlayerDO> playerDataService;
	private final Map<String, Pageable> pages;

	@Autowired
	public PageRouter(SqliteDataService playerDataService, HomePage homePage) {
		this.playerDataService = playerDataService;
		this.pages = new HashMap<>();
		addPage(homePage);
	}

	public int login(String username, String password) {
		Map<String, String> search = new HashMap<>();
		search.put("username", "'" + username + "'");
		List<PlayerDO> searchResults = playerDataService.read(search);

		int response = 0;
		if (searchResults != null && !searchResults.isEmpty()) {
			response = searchResults.get(0).getPassword().equals(password) ? 1 : -1;
		}

		return response;
	}

	public boolean register(String username, String password) throws Exception {
		PlayerDO player = new PlayerDO();
		player.setUsername(username);
		player.setPassword(password);
		return playerDataService.create(player);
	}

	public PageData route(final Route route) throws HubException {
		if (!pages.containsKey(route.getName())) {
			throw new HubException("Cannot find page '" + route.getName() + "'");
		}
		return pages.get(route.getName()).render(route.getParams(), route.getInput());
	}

	private void addPage(Pageable page) {
		if (page != null && page.getClass().isAnnotationPresent(Page.class)) {
			this.pages.put(page.getClass().getAnnotation(Page.class).name(), page);
		}
	}

}
