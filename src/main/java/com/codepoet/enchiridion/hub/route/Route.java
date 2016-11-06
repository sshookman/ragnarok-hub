package com.codepoet.enchiridion.hub.route;

public class Route {

	private RouteNames name;

	public Route() {
	}

	public Route(RouteNames name) {
		this.name = name;
	}

	public RouteNames getName() {
		return name;
	}

	public void setName(RouteNames name) {
		this.name = name;
	}
}
