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

import golem.mud.das.ConnectionManager;
import golem.mud.story.das.model.DisplayComponentDO;

public class DisplayComponentDataServiceTest {

    private static final String PATH = "src/test/resources/library/";

    private static Connection story;
	private static DisplayComponentDataService displayComponentDataService;

	@BeforeClass
	public static void setup() throws Exception {
		story = ConnectionManager.establishConnection(PATH + "GolemTest.glm");
		story = ConnectionManager.initGolem(story);
		displayComponentDataService = new DisplayComponentDataService(story);
	}

    @AfterClass
    public static void teardown() throws Exception {
        File file = new File(PATH + "GolemTest.glm");
        file.delete();
    }

	@Test 
	public void testDisplayComponentDataService() throws Exception {
		testCreate();
		int displayComponentRowId = testReadSearch();
		testUpdate(displayComponentRowId);
        testRead(displayComponentRowId);
        testDelete(displayComponentRowId);
        testEmpty();
	}

	public void testCreate() throws Exception {
		DisplayComponentDO displayComponent = new DisplayComponentDO();
		displayComponent.setEntityId(3);
		displayComponent.setMessage("Sean");
		boolean success = displayComponentDataService.create(displayComponent);
		assertTrue(success);

		DisplayComponentDO badDisplayComponent = new DisplayComponentDO();
		boolean failure = displayComponentDataService.create(badDisplayComponent);
		assertFalse(failure);

		boolean superFailure = displayComponentDataService.create(null);
		assertFalse(superFailure);
	}

	public Integer testReadSearch() throws Exception {
		Integer displayComponentRowId;
		
		List<DisplayComponentDO> displayComponents = displayComponentDataService.read(new HashMap<String, String>());
		assertNotNull(displayComponents);
		assertFalse(displayComponents.isEmpty());

		DisplayComponentDO displayComponent = displayComponents.get(0);
		assertNotNull(displayComponent);
		assertEquals(Integer.valueOf(3), displayComponent.getEntityId());
		assertEquals("Sean", displayComponent.getMessage());
		
		displayComponentRowId = displayComponent.getRowId();

		List<DisplayComponentDO> displayComponentsFiltered = displayComponentDataService.read(displayComponent.toMap());
		assertNotNull(displayComponentsFiltered);
		assertFalse(displayComponentsFiltered.isEmpty());

		DisplayComponentDO displayComponentFiltered = displayComponentsFiltered.get(0);
		assertNotNull(displayComponentFiltered);
		assertEquals(Integer.valueOf(3), displayComponentFiltered.getEntityId());
		assertEquals("Sean", displayComponentFiltered.getMessage());

		Map<String, String> badSearch = new HashMap<String, String>();
		badSearch.put("BAD", "SEARCH");
		List<DisplayComponentDO> displayComponentsBadFilter= displayComponentDataService.read(badSearch);
		assertNotNull(displayComponentsBadFilter);
		assertTrue(displayComponentsBadFilter.isEmpty());

		return displayComponentRowId;
	}

	public void testUpdate(Integer displayComponentRowId) throws Exception {
		DisplayComponentDO displayComponent = new DisplayComponentDO();
		displayComponent.setEntityId(4);
		displayComponent.setMessage("Link");
		boolean success = displayComponentDataService.update(displayComponentRowId, displayComponent);
		assertTrue(success);

		boolean failure = displayComponentDataService.update(displayComponentRowId, new DisplayComponentDO());
		assertFalse(failure);

		boolean failObject = displayComponentDataService.update(displayComponentRowId, null);
		assertFalse(failObject);

		boolean failId = displayComponentDataService.update(null, displayComponent);
		assertFalse(failId);
	}

	public void testRead(Integer displayComponentRowId) throws Exception {
        DisplayComponentDO displayComponent = (DisplayComponentDO) displayComponentDataService.read(displayComponentRowId);
        assertNotNull(displayComponent);
        assertEquals(Integer.valueOf(4), displayComponent.getEntityId());
        assertEquals("Link", displayComponent.getMessage());

        DisplayComponentDO notFound = (DisplayComponentDO) displayComponentDataService.read(123);
        assertNull(notFound);

        Integer nullId = null;
        DisplayComponentDO failure = (DisplayComponentDO) displayComponentDataService.read(nullId);
        assertNull(failure);
	}

	public void testDelete(Integer displayComponentRowId) throws Exception {
        boolean success = displayComponentDataService.delete(displayComponentRowId);
        assertTrue(success);

        boolean failure = displayComponentDataService.delete(null);
        assertFalse(failure);
	}

    public void testEmpty() {
        List<DisplayComponentDO> displayComponents = displayComponentDataService.read(new HashMap<String, String>());
		assertNotNull(displayComponents);
		assertTrue(displayComponents.isEmpty());
    }
}
