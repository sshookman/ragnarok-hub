package com.codepoet.enchiridion.das;

import com.codepoet.enchiridion.das.model.StoryDO;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class StoryDataServiceTest {

	private static final String PATH = "src/test/resources/library/";

	private static Connection golemMudHub;
	private static StoryDataService storyDataService;

	@BeforeClass
	public static void setup() throws Exception {
		golemMudHub = ConnectionManager.establishConnection(PATH + "GolemMudHubTest.gmh");
		golemMudHub = ConnectionManager.initGolemMudHub(golemMudHub);
		storyDataService = new StoryDataService(golemMudHub);
	}

	@AfterClass
	public static void teardown() throws Exception {
		File file = new File(PATH + "GolemMudHubTest.gmh");
		file.delete();
	}

	@Test
	public void testStoryDataService() throws Exception {
		testCreate();
		int storyRowId = testReadSearch();
		testUpdate(storyRowId);
		testRead(storyRowId);
		testDelete(storyRowId);
		testEmpty();
	}

	public void testCreate() throws Exception {
		StoryDO story = new StoryDO();
		story.setName("Sean");
		story.setPath("there");
		boolean success = storyDataService.create(story);
		assertTrue(success);

		StoryDO badStory = new StoryDO();
		boolean failure = storyDataService.create(badStory);
		assertFalse(failure);

		boolean superFailure = storyDataService.create(null);
		assertFalse(superFailure);
	}

	public Integer testReadSearch() throws Exception {
		Integer storyRowId;

		List<StoryDO> storys = storyDataService.read(new HashMap<String, String>());
		assertNotNull(storys);
		assertFalse(storys.isEmpty());

		StoryDO story = storys.get(0);
		assertNotNull(story);
		assertEquals("Sean", story.getName());
		assertEquals("there", story.getPath());

		storyRowId = story.getRowId();

		List<StoryDO> storysFiltered = storyDataService.read(story.toMap());
		assertNotNull(storysFiltered);
		assertFalse(storysFiltered.isEmpty());

		StoryDO storyFiltered = storysFiltered.get(0);
		assertNotNull(storyFiltered);
		assertEquals("Sean", storyFiltered.getName());
		assertEquals("there", storyFiltered.getPath());

		Map<String, String> badSearch = new HashMap<String, String>();
		badSearch.put("BAD", "SEARCH");
		List<StoryDO> storysBadFilter = storyDataService.read(badSearch);
		assertNotNull(storysBadFilter);
		assertTrue(storysBadFilter.isEmpty());

		return storyRowId;
	}

	public void testUpdate(Integer storyRowId) throws Exception {
		StoryDO story = new StoryDO();
		story.setName("Link");
		story.setPath("here");
		boolean success = storyDataService.update(storyRowId, story);
		assertTrue(success);

		boolean failure = storyDataService.update(storyRowId, new StoryDO());
		assertFalse(failure);

		boolean failObject = storyDataService.update(storyRowId, null);
		assertFalse(failObject);

		boolean failId = storyDataService.update(null, story);
		assertFalse(failId);
	}

	public void testRead(Integer storyRowId) throws Exception {
		StoryDO story = (StoryDO) storyDataService.read(storyRowId);
		assertNotNull(story);
		assertEquals("Link", story.getName());
		assertEquals("here", story.getPath());

		StoryDO notFound = (StoryDO) storyDataService.read(123);
		assertNull(notFound);

		Integer nullId = null;
		StoryDO failure = (StoryDO) storyDataService.read(nullId);
		assertNull(failure);
	}

	public void testDelete(Integer storyRowId) throws Exception {
		boolean success = storyDataService.delete(storyRowId);
		assertTrue(success);

		boolean failure = storyDataService.delete(null);
		assertFalse(failure);
	}

	public void testEmpty() {
		List<StoryDO> storys = storyDataService.read(new HashMap<String, String>());
		assertNotNull(storys);
		assertTrue(storys.isEmpty());
	}
}
