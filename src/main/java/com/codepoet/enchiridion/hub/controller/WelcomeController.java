package com.codepoet.enchiridion.hub.controller;

import com.codepoet.enchiridion.hub.model.Request;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class WelcomeController implements Controller {

	@Override
	public Map<String, Object> run(final Request request) {
		List<String> options = new ArrayList<>();
		options.add("Library");
		options.add("Authors");
		options.add("Stories");
		options.add("Config");

		Map<String, Object> model = new HashMap<>();
		model.put("options", options);
		model.put("view", "welcome");

		return model;
	}
}
