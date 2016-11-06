package com.codepoet.enchiridion.hub.route;

import com.codepoet.enchiridion.client.Session;
import com.codepoet.enchiridion.hub.screen.welcome.WelcomeController;

public class Router {

	private final Session session;

	private final WelcomeController welcomeController = new WelcomeController();

	public Router(final Session session) {
		this.session = session;
	}

	public Route resolve(Route route) {
		switch (route.getName()) {
			case WELCOME:
				return welcomeController.welcome(session);
			case LIBRARY:
			case AUTHORS:
			case STORIES:
			case CONFIG:
			default:
				return null;
		}
	}
}
