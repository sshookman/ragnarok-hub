package codepoet.ragnarok.hub;

import codepoet.ragnarok.annotation.Page;
import codepoet.venalartificer.TemplateBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Page(name = "welcome")
public class WelcomePage implements Pageable {

	private Map<String, Object> templateData;
	private TemplateBuilder templateBuilder;

	@Autowired
	public WelcomePage(TemplateBuilder templateBuilder) {
		this.templateBuilder = templateBuilder;

		this.templateData = new HashMap<>();
		List<Map<String, String>> options = new ArrayList<>();
		options.add(buildOption("1", "Start"));
		options.add(buildOption("2", "Help"));
		options.add(buildOption("3", "Exit"));
		templateData.put("options", options);
	}

	private Map<String, String> buildOption(String index, String value) {
		Map<String, String> option = new HashMap<>();
		option.put("index", index);
		option.put("value", value);
		return option;
	}

	@Override
	public PageData render(Map<String, String> params) {

		String renderText = templateBuilder.render("Welcome", templateData);
		return new PageData.Builder(renderText)
				.route("1", new Route.Builder("start").build())
				.route("2", new Route.Builder("help").build())
				.route("3", new Route.Builder("exit").build())
				.build();
	}
}
