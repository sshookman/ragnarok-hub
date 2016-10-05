package golem.mud.story.das;

import golem.mud.common.das.ConnectionManager;
import golem.mud.story.das.model.DisplayComponentDO;
import golem.mud.story.das.model.StateDO;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

public class StateDataServiceTest {

	private static final String PATH = "src/test/resources/library/";

	private static Connection story;
	private static StateDataService stateDataService;

	@BeforeClass
	public static void setup() throws Exception {
		story = ConnectionManager.establishConnectionInMemory(PATH + "GolemTest.glm");
		story = ConnectionManager.initGolem(story);
		story = ConnectionManager.initSaveGame(story);
		stateDataService = new StateDataService(story);
	}

	@AfterClass
	public static void teardown() throws Exception {
		File file = new File(PATH + "GolemTest.glm");
		file.delete();
	}

	@Test
	public void testEntityDataService() throws Exception {
		testCreate();
		int stateRowId = testReadSearch();
		testUpdate(stateRowId);
		testRead(stateRowId);
		testDelete(stateRowId);
		testEmpty();
	}

	public void testCreate() throws Exception {
		StateDO state = new StateDO();
		state.setLocationEntityId(12);
		boolean success = stateDataService.create(state);
		assertTrue(success);

		boolean superFailure = stateDataService.create(null);
		assertFalse(superFailure);
	}

	public Integer testReadSearch() throws Exception {
		Integer stateRowId;

		List<StateDO> states = stateDataService.read(new HashMap<>());
		assertNotNull(states);
		assertFalse(states.isEmpty());

		StateDO state = states.get(0);
		assertNotNull(state);
		assertNotNull(state.getRowId());
		assertEquals(Integer.valueOf(12), state.getLocationEntityId());

		stateRowId = state.getRowId();

		Map<String, String> badSearch = new HashMap<>();
		badSearch.put("BAD", "SEARCH");
		List<StateDO> statesBadFilter = stateDataService.read(badSearch);
		assertNotNull(statesBadFilter);
		assertTrue(statesBadFilter.isEmpty());

		return stateRowId;
	}

	public void testUpdate(Integer stateRowId) throws Exception {
		StateDO state = new StateDO();
		state.setLocationEntityId(4);
		boolean success = stateDataService.update(stateRowId, state);
		assertTrue(success);

		boolean failure = stateDataService.update(stateRowId, new DisplayComponentDO());
		assertFalse(failure);

		boolean failObject = stateDataService.update(stateRowId, null);
		assertFalse(failObject);

		boolean failId = stateDataService.update(null, state);
		assertFalse(failId);
	}

	public void testRead(Integer stateRowId) throws Exception {
		StateDO state = (StateDO) stateDataService.read(stateRowId);
		assertNotNull(state);
		assertNotNull(state.getRowId());
		assertEquals(Integer.valueOf(4), state.getLocationEntityId());

		StateDO notFound = (StateDO) stateDataService.read(123);
		assertNull(notFound);

		Integer nullId = null;
		StateDO failure = (StateDO) stateDataService.read(nullId);
		assertNull(failure);
	}

	public void testDelete(Integer stateRowId) throws Exception {
		boolean success = stateDataService.delete(stateRowId);
		assertTrue(success);

		boolean failure = stateDataService.delete(null);
		assertFalse(failure);
	}

	public void testEmpty() {
		List<StateDO> states = stateDataService.read(new HashMap<>());
		assertNotNull(states);
		assertTrue(states.isEmpty());
	}
}
