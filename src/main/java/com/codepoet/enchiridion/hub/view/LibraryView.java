package com.codepoet.enchiridion.hub.view;

import com.codepoet.enchiridion.das.model.StoryDO;
import com.codepoet.enchiridion.hub.model.Request;
import com.codepoet.enchiridion.render.Renderer;
import java.util.List;
import java.util.Map;

public class LibraryView implements View {

	@Override
	public Request render(Renderer renderer, Map<String, Object> model) {
		List<StoryDO> stories = (List<StoryDO>) model.get("stories");

		stories.forEach((story) -> {
			renderer.write(story.getName());
			renderer.endl(1);
		});

		renderer.endl(2);
		String input = renderer.prompt();

		Request request = new Request.Builder()
				.controller("welcome")
				.build();

		return request;
	}
}
