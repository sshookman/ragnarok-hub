package com.codepoet.enchiridion.view;

import com.codepoet.enchiridion.das.model.StoryDO;
import com.codepoet.enchiridion.render.Renderer;
import java.util.List;
import java.util.Map;

public class LibraryView implements View {

	@Override
	public String render(Renderer renderer, Map<String, Object> model) {
		List<StoryDO> stories = (List<StoryDO>) model.get("stories");

		stories.forEach((story) -> {
			renderer.write(story.getName());
			renderer.endl(1);
		});

		renderer.endl(2);
		return renderer.prompt();
	}
}
