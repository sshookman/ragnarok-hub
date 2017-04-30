package codepoet.ragnarok.hub.model;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private String route;
	private Map<String, Object> parameters;

	public Request(final String route, final Map<String, Object> parameters) {
		this.route = route;
		this.parameters = parameters;
	}

	public String getRoute() {
		return route;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public Object getParameter(final String key) {
		return parameters.get(key);
	}

	public static class Builder {

		private String route;
		private Map<String, Object> parameters;

		public Builder() {
			parameters = new HashMap<>();
		}

		public Builder route(final String route) {
			this.route = route;
			return this;
		}

		public Builder param(final String key, final Object value) {
			this.parameters.put(key, value);
			return this;
		}

		public Request build() {
			return new Request(route, parameters);
		}
	}
}
