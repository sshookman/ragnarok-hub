package codepoet.ragnarok.hub;

import java.util.HashMap;
import java.util.Map;

public class PageData {

	private String display;
	private String prompt;
	private Map<String, Route> routes;

	private PageData(String display, String prompt, Map<String, Route> routes) {
		this.display = display;
		this.prompt = prompt;
		this.routes = routes;
	}

	public String getDisplay() {
		return display;
	}

	public Map<String, Route> getRoutes() {
		return routes;
	}

	public String getPrompt() {
		return prompt;
	}

	public static class Builder {

		private String display;
		private String prompt;
		private Map<String, Route> routes;

		public Builder(String display, String prompt) {
			this.display = display;
			this.prompt = prompt;
			this.routes = new HashMap<>();
		}

		public Builder route(String key, Route value) {
			routes.put(key, value);
			return this;
		}

		public PageData build() {
			return new PageData(display, prompt, routes);
		}
	}
}
