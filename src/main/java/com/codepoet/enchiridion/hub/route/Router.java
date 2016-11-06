package com.codepoet.enchiridion.hub.route;

import com.codepoet.enchiridion.hub.controller.HubControllers;

public class Router {

	private HubControllers controllers;
	private String sessionId;

	public Router(final HubControllers controllers, final String sessionId) {
		this.controllers = controllers;
		this.sessionId = sessionId;
	}

	public Route resolve(Route route) {
		switch (route.getName()) {
			case WELCOME:
				return controllers.welcomeController.welcome(sessionId);
			case LIBRARY:
			case AUTHORS:
			case STORIES:
			case CONFIG:
			default:
				return null;
		}
	}
}
