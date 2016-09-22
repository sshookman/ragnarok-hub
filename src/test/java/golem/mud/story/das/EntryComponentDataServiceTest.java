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
import golem.mud.story.das.model.EntryComponentDO;

public class EntryComponentDataServiceTest {

    private static final String PATH = "src/test/resources/library/";

    private static Connection story;
	private static EntryComponentDataService entryComponentDataService;

	@BeforeClass
	public static void setup() throws Exception {
		story = ConnectionManager.establishConnection(PATH + "GolemTest.glm");
		story = ConnectionManager.initGolem(story);
		entryComponentDataService = new EntryComponentDataService(story);
	}

    @AfterClass
    public static void teardown() throws Exception {
        File file = new File(PATH + "GolemTest.glm");
        file.delete();
    }

	@Test 
	public void testEntryComponentDataService() throws Exception {
		testCreate();
		int entryComponentRowId = testReadSearch();
		testUpdate(entryComponentRowId);
        testRead(entryComponentRowId);
        testDelete(entryComponentRowId);
        testEmpty();
	}

	public void testCreate() throws Exception {
		EntryComponentDO entryComponent = new EntryComponentDO();
		entryComponent.setEntityId(3);
		entryComponent.setPosition(123);
		boolean success = entryComponentDataService.create(entryComponent);
		assertTrue(success);

		EntryComponentDO badEntryComponent = new EntryComponentDO();
		boolean failure = entryComponentDataService.create(badEntryComponent);
		assertFalse(failure);

		boolean superFailure = entryComponentDataService.create(null);
		assertFalse(superFailure);
	}

	public Integer testReadSearch() throws Exception {
		Integer entryComponentRowId;
		
		List<EntryComponentDO> entryComponents = entryComponentDataService.read(new HashMap<String, String>());
		assertNotNull(entryComponents);
		assertFalse(entryComponents.isEmpty());

		EntryComponentDO entryComponent = entryComponents.get(0);
		assertNotNull(entryComponent);
		assertEquals(Integer.valueOf(3), entryComponent.getEntityId());
		assertEquals(Integer.valueOf(123), entryComponent.getPosition());
		
		entryComponentRowId = entryComponent.getRowId();

		List<EntryComponentDO> entryComponentsFiltered = entryComponentDataService.read(entryComponent.toMap());
		assertNotNull(entryComponentsFiltered);
		assertFalse(entryComponentsFiltered.isEmpty());

		EntryComponentDO entryComponentFiltered = entryComponentsFiltered.get(0);
		assertNotNull(entryComponentFiltered);
		assertEquals(Integer.valueOf(3), entryComponentFiltered.getEntityId());
		assertEquals(Integer.valueOf(123), entryComponentFiltered.getPosition());

		Map<String, String> badSearch = new HashMap<String, String>();
		badSearch.put("BAD", "SEARCH");
		List<EntryComponentDO> entryComponentsBadFilter= entryComponentDataService.read(badSearch);
		assertNotNull(entryComponentsBadFilter);
		assertTrue(entryComponentsBadFilter.isEmpty());

		return entryComponentRowId;
	}

	public void testUpdate(Integer entryComponentRowId) throws Exception {
		EntryComponentDO entryComponent = new EntryComponentDO();
		entryComponent.setEntityId(4);
		entryComponent.setPosition(321);
		boolean success = entryComponentDataService.update(entryComponentRowId, entryComponent);
		assertTrue(success);

		boolean failure = entryComponentDataService.update(entryComponentRowId, new EntryComponentDO());
		assertFalse(failure);

		boolean failObject = entryComponentDataService.update(entryComponentRowId, null);
		assertFalse(failObject);

		boolean failId = entryComponentDataService.update(null, entryComponent);
		assertFalse(failId);
	}

	public void testRead(Integer entryComponentRowId) throws Exception {
        EntryComponentDO entryComponent = (EntryComponentDO) entryComponentDataService.read(entryComponentRowId);
        assertNotNull(entryComponent);
        assertEquals(Integer.valueOf(4), entryComponent.getEntityId());
        assertEquals(Integer.valueOf(321), entryComponent.getPosition());

        EntryComponentDO notFound = (EntryComponentDO) entryComponentDataService.read(123);
        assertNull(notFound);

        Integer nullId = null;
        EntryComponentDO failure = (EntryComponentDO) entryComponentDataService.read(nullId);
        assertNull(failure);
	}

	public void testDelete(Integer entryComponentRowId) throws Exception {
        boolean success = entryComponentDataService.delete(entryComponentRowId);
        assertTrue(success);

        boolean failure = entryComponentDataService.delete(null);
        assertFalse(failure);
	}

    public void testEmpty() {
        List<EntryComponentDO> entryComponents = entryComponentDataService.read(new HashMap<String, String>());
		assertNotNull(entryComponents);
		assertTrue(entryComponents.isEmpty());
    }
}
