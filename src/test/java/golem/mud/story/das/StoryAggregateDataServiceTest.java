package golem.mud.story.das;

import golem.mud.common.das.ConnectionManager;
import golem.mud.story.das.model.*;
import java.io.File;
import java.sql.Connection;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class StoryAggregateDataServiceTest {

	private static final String PATH = "src/test/resources/library/";

	private static final String START_MESSAGE
			= "The human race is over. The Checkered flag has dropped.\n"
			+ "The Desolate is rising. The beating heart has stopped.";

	private static Connection story;
	private static StoryAggregateDataService services;

	@BeforeClass
	public static void setup() throws Exception {
		story = ConnectionManager.establishConnection(PATH + "GolemTest.glm");
		story = ConnectionManager.initGolem(story);
		services = new StoryAggregateDataService(PATH + "GolemTest.glm");
	}

	@AfterClass
	public static void teardown() throws Exception {
		File file = new File(PATH + "GolemTest.glm");
		file.delete();
	}

	@Test
	public void testStoryAggregateDataService() throws Exception {
		assertNotNull(services.entityService);
		assertNotNull(services.displayService);
		assertNotNull(services.pathService);
	}

	@Test
	public void testGetStartingPoint() {

		assertNull(services.getStartingLocation());

		assertTrue(services.entityService.create(new EntityDO()));

		EntryComponentDO entry = new EntryComponentDO();
		entry.setEntityId(1);
		entry.setPosition(1);
		assertTrue(services.entryService.create(entry));

		DisplayComponentDO display = new DisplayComponentDO();
		display.setEntityId(1);
		display.setMessage(START_MESSAGE);
		assertTrue(services.displayService.create(display));

		DisplayComponentDO startingPoint = services.getStartingLocation();
		assertNotNull(startingPoint);
		assertNotNull(startingPoint.getRowId());
		assertEquals(Integer.valueOf(1), startingPoint.getEntityId());
		assertEquals(START_MESSAGE, startingPoint.getMessage());
	}
}
