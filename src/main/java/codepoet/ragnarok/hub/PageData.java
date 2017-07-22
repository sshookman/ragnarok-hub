package codepoet.ragnarok.hub;

import java.util.HashMap;
import java.util.Map;

public class PageData {

	private String display;
	private Map<String, Route> routes;

	private PageData(String display, Map<String, Route> routes) {
		this.display = display;
		this.routes = routes;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Map<String, Route> getRoutes() {
		return routes;
	}

	public void setRoutes(Map<String, Route> routes) {
		this.routes = routes;
	}

	public static class Builder {

		private String display;
		private Map<String, Route> routes;

		public Builder(String display) {
			this.display = display;
			this.routes = new HashMap<>();
		}

		public Builder route(String key, Route value) {
			routes.put(key, value);
			return this;
		}

		public PageData build() {
			return new PageData(display, routes);
		}
	}
}
