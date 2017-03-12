package codepoet.ragnarok.hub.view;

import codepoet.ragnarok.hub.model.Request;
import codepoet.ragnarok.hub.template.TemplateUtil;
import static codepoet.ragnarok.hub.template.TemplateUtil.WELCOME_TEMPLATE;
import codepoet.ragnarok.render.Renderer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WelcomeView implements View {

	@Override
	public Request render(final Renderer renderer, Map<String, Object> model) {
		String welcome = buildWelcomeView(model);
		renderer.write(welcome, Renderer.PURPLE);

		String input = renderer.prompt();

		String controller = null;
		switch (input) {
			case "1":
				controller = "library";
				break;
			default:
				controller = "";
				break;
		}

		return new Request.Builder().controller(controller).build();
	}

	public String buildWelcomeView(Map<String, Object> model) {
		String options = "";

		if (model != null && model.containsKey("options")) {
			Object optionsObject = model.get("options");
			if (optionsObject instanceof List) {
				options = buildOptionsList("", (List<String>) optionsObject);
			}
		}

		Map<String, String> data = new HashMap<String, String>();
		data.put("options", options);

		return TemplateUtil.compose(WELCOME_TEMPLATE, data);
	}

	private String buildOptionsList(final String options, final List<String> optionList) {

		if (optionList.isEmpty()) {
			return options;
		}

		StringBuilder optionsBuilder = new StringBuilder()
				.append("    [").append(optionList.size()).append("] ")
				.append(optionList.get(optionList.size() - 1)).append("\n")
				.append(options);

		List<String> reducedOptionList = new ArrayList<>(optionList.subList(0, optionList.size() - 1));

		return buildOptionsList(optionsBuilder.toString(), reducedOptionList);
	}
}
