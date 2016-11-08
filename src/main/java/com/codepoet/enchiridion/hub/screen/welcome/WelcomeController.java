package com.codepoet.enchiridion.hub.screen.welcome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class WelcomeController {

	public Map<String, Object> welcome() {
		List<String> options = new ArrayList<>();
		options.add("Library");
		options.add("Authors");
		options.add("Stories");
		options.add("Config");

		Map<String, Object> model = new HashMap<>();
		model.put("options", options);

		return model;
	}
}
