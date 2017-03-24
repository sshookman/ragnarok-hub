package codepoet.ragnarok.hub.controller;

import codepoet.ragnarok.das.model.StoryDO;
import codepoet.ragnarok.hub.model.Request;
import codepoet.vaultmonkey.service.SqliteDataService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LibraryController implements Controller {

	@Autowired
	private SqliteDataService storyDataService;

	@Override
	public Map<String, Object> run(final Request request) {
		List<StoryDO> stories = storyDataService.read(new HashMap<>());

		Map<String, Object> model = new HashMap<>();
		model.put("stories", stories);
		model.put("view", "library");

		return model;
	}
}
