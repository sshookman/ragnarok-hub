package codepoet.ragnarok.hub;

import codepoet.ragnarok.annotation.Page;
import codepoet.ragnarok.exception.HubException;
import codepoet.ragnarok.hub.page.LoginPage;
import codepoet.ragnarok.hub.page.WelcomePage;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageRouter {

	private final Map<String, Pageable> pages;

	@Autowired
	public PageRouter(WelcomePage welcomePage, LoginPage loginPage) {
		this.pages = new HashMap<>();
		addPage(welcomePage);
		addPage(loginPage);
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
