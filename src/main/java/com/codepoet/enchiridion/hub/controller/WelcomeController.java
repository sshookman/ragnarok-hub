package com.codepoet.enchiridion.hub.controller;

import com.codepoet.enchiridion.common.telnet.TelnetSession;
import com.codepoet.enchiridion.common.telnet.TelnetSessionManager;
import com.codepoet.enchiridion.hub.route.Route;
import com.codepoet.enchiridion.hub.route.RouteNames;
import com.codepoet.enchiridion.hub.view.WelcomeView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WelcomeController {

	@Autowired
	private TelnetSessionManager sessionManager;

	@Autowired
	private WelcomeView welcomeView;

	public Route welcome(final String sessionId) {
		//Obtain Session
		TelnetSession session = sessionManager.getSession(sessionId);

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
