package com.codepoet.enchiridion.hub.route;

import java.util.HashMap;
import java.util.Map;

public class Route {

	private RouteNames name;
	private Map<String, Object> properties;

	public Route() {
		this.properties = new HashMap<>();
	}

	public Route(RouteNames name) {
		this.name = name;
		this.properties = new HashMap<>();
	}

	public RouteNames getName() {
		return name;
	}

	public void setName(RouteNames name) {
		this.name = name;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}

	public void addProperty(String name, Object value) {
		this.properties.put(name, value);
	}
}
