package codepoet.ragnarok.hub;

import codepoet.ragnarok.annotation.Page;
import codepoet.venalartificer.TemplateBuilder;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
@Page(name = "welcome")
public class WelcomePage implements Pageable {

	private TemplateBuilder templateBuilder;

	public WelcomePage(TemplateBuilder templateBuilder) {
		this.templateBuilder = templateBuilder;
	}

	@Override
	public PageData render(Map<String, String> params) {
		String renderText = templateBuilder.render("Welcome", null);
		return new PageData.Builder(renderText).build();
	}
}
