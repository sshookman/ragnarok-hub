package golem.mud.story.das;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.sql.Connection;
import java.io.File;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import golem.mud.common.das.ConnectionManager;

public class StoryAggregateDataServiceTest {

    private static final String PATH = "src/test/resources/library/";

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
}
