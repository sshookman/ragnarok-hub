package com.codepoet.enchiridion.hub.controller;

import com.codepoet.enchiridion.hub.view.WelcomeView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WelcomeController {

	@Autowired
	private WelcomeView welcomeView;

	public void welcome(final String sessionId) {
		List<String> options = new ArrayList<>();
		options.add("Library");
		options.add("Authors");
		options.add("Stories");
		options.add("Config");

		Map<String, Object> model = new HashMap<>();
		model.put("options", options);

		welcomeView.welcome(sessionId, model);
	}
}
