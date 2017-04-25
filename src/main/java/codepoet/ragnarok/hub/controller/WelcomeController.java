package codepoet.ragnarok.hub.controller;

import codepoet.ragnarok.hub.model.Request;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class WelcomeController implements Controller {

	@Override
	public Map<String, Object> run(final Request request) {
		List<Object> options = new ArrayList<>();
		options.add(buildOption(1, "Library"));
		options.add(buildOption(2, "Authors"));
		options.add(buildOption(3, "Stories"));
		options.add(buildOption(4, "Config"));

		Map<String, Object> model = new HashMap<>();
		model.put("options", options);
		model.put("view", "welcome");

		return model;
	}

	private Map<String, Object> buildOption(final Integer index, final String value) {
		Map<String, Object> option = new HashMap<>();
		option.put("index", index);
		option.put("value", value);
		return option;
	}
}
