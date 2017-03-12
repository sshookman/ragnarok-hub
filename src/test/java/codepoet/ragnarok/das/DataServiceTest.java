package codepoet.ragnarok.das;

import codepoet.ragnarok.das.model.StoryDO;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class DataServiceTest {

	private static final String PATH = "src/test/resources/library/DataServiceTest.gmh";
	private Connection connection;
	private DataService<StoryDO> testDataService;

	@AfterClass
	public static void teardown() {
		File file = new File(PATH);
		file.delete();
	}

	@Before
	public void setUp() throws Exception {
		connection = ConnectionManager.establishConnection(PATH);
		testDataService = new DataService(new StoryDO(), connection);
	}

	@Test
	public void testErrors() {
		Integer noInt = null;
		Map<String, String> noMap = null;
		assertNull(testDataService.read(noInt));
		assertNull(testDataService.read(noMap));
		assertNull(testDataService.create(null));
		assertNull(testDataService.update(null, new StoryDO()));
		assertNull(testDataService.update(1, null));
		assertNull(testDataService.delete(null));
		assertTrue(testDataService.read(new HashMap<>()).isEmpty());
		assertFalse(testDataService.create(new StoryDO()));
	}

	@Test
	public void testCRUD() throws FileNotFoundException, SQLException {
		ConnectionManager.initGolemMudHub(connection);
		testRead_Map_Empty();
		testCreate();
		testRead_Integer();
		testUpdate();
		testRead_Map();
		testDelete();
		testRead_Map_Empty();

	}

	public void testRead_Map_Empty() {
		List<StoryDO> stories = testDataService.read(new HashMap<>());
		assertNotNull(stories);
		assertTrue(stories.isEmpty());
	}

	public void testRead_Map() {
		List<StoryDO> stories = testDataService.read(new HashMap<>());
		assertNotNull(stories);
		assertEquals(1, stories.size());

		StoryDO story = stories.get(0);
		assertNotNull(story);
		assertEquals(Integer.valueOf(1), story.getRowId());
		assertEquals("MyStoryUpdated", story.getName());
		assertEquals("/there", story.getPath());
	}

	public void testRead_Integer() {
		StoryDO story = (StoryDO) testDataService.read(1);
		assertNotNull(story);
		assertEquals(Integer.valueOf(1), story.getRowId());
		assertEquals("MyStory", story.getName());
		assertEquals("/here", story.getPath());

		assertNull(testDataService.read(2));
	}

	public void testCreate() {
		StoryDO story = new StoryDO();
		story.setName("MyStory");
		story.setPath("/here");
		assertTrue(testDataService.create(story));
	}

	public void testUpdate() {
		StoryDO story = new StoryDO();
		story.setName("MyStoryUpdated");
		story.setPath("/there");
		assertTrue(testDataService.update(1, story));
	}

	public void testDelete() {
		assertTrue(testDataService.delete(1));
	}

}
