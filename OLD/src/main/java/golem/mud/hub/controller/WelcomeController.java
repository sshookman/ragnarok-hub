package golem.mud.hub.controller;

import golem.mud.common.telnet.TelnetSession;
import golem.mud.hub.view.WelcomeView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WelcomeController {

	private final WelcomeView welcomeView;
	private final List<String> options;

	public WelcomeController(TelnetSession session) {
		welcomeView = new WelcomeView(session);
		options = new ArrayList<>();
		options.add("Library");
		options.add("Authors");
		options.add("Stories");
		options.add("Config");
	}

	public void welcome() {
		Map<String, Object> model = new HashMap<>();
		model.put("options", options);

		welcomeView.welcome(model);
	}
}
