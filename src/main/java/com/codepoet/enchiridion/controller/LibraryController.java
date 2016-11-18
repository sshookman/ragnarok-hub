package com.codepoet.enchiridion.controller;

import com.codepoet.enchiridion.das.StoryDataService;
import com.codepoet.enchiridion.das.model.StoryDO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibraryController implements Controller {

	@Autowired
	private StoryDataService service;

	@Override
	public Map<String, Object> run() {
		List<StoryDO> stories = service.read(new HashMap<>());

		Map<String, Object> model = new HashMap<>();
		model.put("stories", stories);
		model.put("view", "library");

		return model;
	}
}
