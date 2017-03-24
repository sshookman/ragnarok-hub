package codepoet.ragnarok.hub.controller;

import codepoet.ragnarok.das.model.StoryDO;
import codepoet.vaultmonkey.service.SqliteDataService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyMap;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

public class LibraryControllerTest {

	@Mock
	private SqliteDataService storyDataService;

	@InjectMocks
	private LibraryController libraryController;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		List<StoryDO> stories = new ArrayList<>();
		stories.add(buildStory(1));
		stories.add(buildStory(2));
		stories.add(buildStory(3));

		when(storyDataService.read(anyMap())).thenReturn(stories);
	}

	@Test
	public void testRun() {
		Map<String, Object> response = libraryController.run(null);
		assertNotNull(response);
		assertEquals("library", response.get("view"));

		List<StoryDO> stories = (List<StoryDO>) response.get("stories");
		assertNotNull(stories);
		assertEquals(3, stories.size());

		for (Integer x = 0; x < 3; x++) {
			assertEquals(stories.get(x).getId().toString(), stories.get(x).getName());
			assertEquals(stories.get(x).getId().toString(), stories.get(x).getPath());
		}
	}

	private StoryDO buildStory(Integer i) {
		StoryDO story = new StoryDO();
		story.setId(i);
		story.setName(i.toString());
		story.setPath(i.toString());
		return story;
	}
}
