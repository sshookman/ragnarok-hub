package com.codepoet.enchiridion.hub.model;

import java.util.HashMap;
import java.util.Map;

public class Request {

	private String controller;
	private Map<String, Object> parameters;

	public Request(final String controller, final Map<String, Object> parameters) {
		this.controller = controller;
		this.parameters = parameters;
	}

	public String getController() {
		return controller;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public Object getParameter(final String key) {
		return parameters.get(key);
	}

	public static class Builder {

		private String controller;
		private Map<String, Object> parameters;

		public Builder() {
			parameters = new HashMap<>();
		}

		public Builder controller(final String controller) {
			this.controller = controller;
			return this;
		}

		public Builder param(final String key, final Object value) {
			this.parameters.put(key, value);
			return this;
		}

		public Request build() {
			return new Request(controller, parameters);
		}
	}
}
