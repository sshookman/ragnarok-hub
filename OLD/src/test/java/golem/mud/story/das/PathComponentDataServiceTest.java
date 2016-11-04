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
import golem.mud.common.enums.Direction;
import golem.mud.story.das.model.PathComponentDO;

public class PathComponentDataServiceTest {

    private static final String PATH = "src/test/resources/library/";

    private static Connection story;
	private static PathComponentDataService pathComponentDataService;

	@BeforeClass
	public static void setup() throws Exception {
		story = ConnectionManager.establishConnection(PATH + "GolemTest.glm");
		story = ConnectionManager.initGolem(story);
		pathComponentDataService = new PathComponentDataService(story);
	}

    @AfterClass
    public static void teardown() throws Exception {
        File file = new File(PATH + "GolemTest.glm");
        file.delete();
    }

	@Test 
	public void testPathComponentDataService() throws Exception {
		testCreate();
		int pathComponentRowId = testReadSearch();
		testUpdate(pathComponentRowId);
        testRead(pathComponentRowId);
        testDelete(pathComponentRowId);
        testEmpty();
	}

	public void testCreate() throws Exception {
		PathComponentDO pathComponent = new PathComponentDO();
		pathComponent.setEntityId(3);
		pathComponent.setDestEntityId(5);
		pathComponent.setName("name");
		pathComponent.setDirection(Direction.NORTHWEST);
		pathComponent.setDisplayMessage("DIS MESS");
		pathComponent.setTransitionMessage("TRA MESS");
		boolean success = pathComponentDataService.create(pathComponent);
		assertTrue(success);

		PathComponentDO badPathComponent = new PathComponentDO();
		boolean failure = pathComponentDataService.create(badPathComponent);
		assertFalse(failure);

		boolean superFailure = pathComponentDataService.create(null);
		assertFalse(superFailure);
	}

	public Integer testReadSearch() throws Exception {
		Integer pathComponentRowId;
		
		List<PathComponentDO> pathComponents = pathComponentDataService.read(new HashMap<String, String>());
		assertNotNull(pathComponents);
		assertFalse(pathComponents.isEmpty());

		PathComponentDO pathComponent = pathComponents.get(0);
		assertNotNull(pathComponent);
		assertEquals(Integer.valueOf(3), pathComponent.getEntityId());
		assertEquals(Integer.valueOf(5), pathComponent.getDestEntityId());
		assertEquals("name", pathComponent.getName());
		assertEquals(Direction.NORTHWEST, pathComponent.getDirection());
		assertEquals("DIS MESS", pathComponent.getDisplayMessage());
		assertEquals("TRA MESS", pathComponent.getTransitionMessage());
		
		pathComponentRowId = pathComponent.getRowId();

		List<PathComponentDO> pathComponentsFiltered = pathComponentDataService.read(pathComponent.toMap());
		assertNotNull(pathComponentsFiltered);
		assertFalse(pathComponentsFiltered.isEmpty());

		PathComponentDO pathComponentFiltered = pathComponentsFiltered.get(0);
		assertNotNull(pathComponentFiltered);
		assertEquals(Integer.valueOf(3), pathComponentFiltered.getEntityId());
		assertEquals(Integer.valueOf(5), pathComponentFiltered.getDestEntityId());
		assertEquals("name", pathComponentFiltered.getName());
		assertEquals(Direction.NORTHWEST, pathComponentFiltered.getDirection());
		assertEquals("DIS MESS", pathComponentFiltered.getDisplayMessage());
		assertEquals("TRA MESS", pathComponentFiltered.getTransitionMessage());

		Map<String, String> badSearch = new HashMap<String, String>();
		badSearch.put("BAD", "SEARCH");
		List<PathComponentDO> pathComponentsBadFilter= pathComponentDataService.read(badSearch);
		assertNotNull(pathComponentsBadFilter);
		assertTrue(pathComponentsBadFilter.isEmpty());

		return pathComponentRowId;
	}

	public void testUpdate(Integer pathComponentRowId) throws Exception {
		PathComponentDO pathComponent = new PathComponentDO();
		pathComponent.setEntityId(2);
		pathComponent.setDestEntityId(6);
		pathComponent.setName("eman");
		pathComponent.setDirection(Direction.SOUTHEAST);
		pathComponent.setDisplayMessage("DAT MESS");
		pathComponent.setTransitionMessage("TAR MESS");
		boolean success = pathComponentDataService.update(pathComponentRowId, pathComponent);
		assertTrue(success);

		boolean failure = pathComponentDataService.update(pathComponentRowId, new PathComponentDO());
		assertFalse(failure);

		boolean failObject = pathComponentDataService.update(pathComponentRowId, null);
		assertFalse(failObject);

		boolean failId = pathComponentDataService.update(null, pathComponent);
		assertFalse(failId);
	}

	public void testRead(Integer pathComponentRowId) throws Exception {
        PathComponentDO pathComponent = (PathComponentDO) pathComponentDataService.read(pathComponentRowId);
        assertNotNull(pathComponent);
		assertEquals(Integer.valueOf(2), pathComponent.getEntityId());
		assertEquals(Integer.valueOf(6), pathComponent.getDestEntityId());
		assertEquals("eman", pathComponent.getName());
		assertEquals(Direction.SOUTHEAST, pathComponent.getDirection());
		assertEquals("DAT MESS", pathComponent.getDisplayMessage());
		assertEquals("TAR MESS", pathComponent.getTransitionMessage());

        PathComponentDO notFound = (PathComponentDO) pathComponentDataService.read(123);
        assertNull(notFound);

        Integer nullId = null;
        PathComponentDO failure = (PathComponentDO) pathComponentDataService.read(nullId);
        assertNull(failure);
	}

	public void testDelete(Integer pathComponentRowId) throws Exception {
        boolean success = pathComponentDataService.delete(pathComponentRowId);
        assertTrue(success);

        boolean failure = pathComponentDataService.delete(null);
        assertFalse(failure);
	}

    public void testEmpty() {
        List<PathComponentDO> pathComponents = pathComponentDataService.read(new HashMap<String, String>());
		assertNotNull(pathComponents);
		assertTrue(pathComponents.isEmpty());
    }
}
