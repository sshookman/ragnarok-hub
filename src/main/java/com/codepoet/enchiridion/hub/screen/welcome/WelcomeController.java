package com.codepoet.enchiridion.hub.screen.welcome;

import com.codepoet.enchiridion.client.Session;
import com.codepoet.enchiridion.hub.route.Route;
import com.codepoet.enchiridion.hub.route.RouteNames;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WelcomeController {

	private WelcomeView welcomeView = new WelcomeView();

	public Route welcome(final Session session) {
		//Build Model
		List<String> options = new ArrayList<>();
		options.add("Library");
		options.add("Authors");
		options.add("Stories");
		options.add("Config");
		Map<String, Object> model = new HashMap<>();
		model.put("options", options);

		//Render View with Model
		String input = welcomeView.render(session.getRenderer(), model);

		//Build Route
		Route route = new Route();
		switch (input) {
			case "1":
				route.setName(RouteNames.LIBRARY);
				break;
			case "2":
				route.setName(RouteNames.AUTHORS);
				break;
			case "3":
				route.setName(RouteNames.STORIES);
				break;
			case "4":
				route.setName(RouteNames.CONFIG);
				break;
			default:
				route = null;
				break;
		}

		return route;
	}
}
