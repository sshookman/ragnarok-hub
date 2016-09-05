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
import golem.mud.story.das.model.EntityDO;

public class EntityDataServiceTest {

    private static final String PATH = "src/test/resources/library/";

    private static Connection story;
	private static EntityDataService entityDataService;

	@BeforeClass
	public static void setup() throws Exception {
		story = ConnectionManager.establishConnection(PATH + "GolemTest.glm");
		story = ConnectionManager.initGolem(story);
		entityDataService = new EntityDataService(story);
	}

    @AfterClass
    public static void teardown() throws Exception {
        File file = new File(PATH + "GolemTest.glm");
        file.delete();
    }

	@Test 
	public void testEntityDataService() throws Exception {
		testCreate();
		int entityRowId = testReadSearch();
        testRead(entityRowId);
        testDelete(entityRowId);
        testEmpty();
	}

	public void testCreate() throws Exception {
		EntityDO entity = new EntityDO();
		boolean success = entityDataService.create(entity);
		assertTrue(success);

		boolean superFailure = entityDataService.create(null);
		assertFalse(superFailure);
	}

	public Integer testReadSearch() throws Exception {
		Integer entityRowId;
		
		List<EntityDO> entities = entityDataService.read(new HashMap<String, String>());
		assertNotNull(entities);
		assertFalse(entities.isEmpty());

		EntityDO entity = entities.get(0);
		assertNotNull(entity);
        assertNotNull(entity.getRowId());
		
		entityRowId = entity.getRowId();

		Map<String, String> badSearch = new HashMap<String, String>();
		badSearch.put("BAD", "SEARCH");
		List<EntityDO> entitiesBadFilter= entityDataService.read(badSearch);
		assertNotNull(entitiesBadFilter);
		assertTrue(entitiesBadFilter.isEmpty());

		return entityRowId;
	}

	public void testRead(Integer entityRowId) throws Exception {
        EntityDO entity = (EntityDO) entityDataService.read(entityRowId);
        assertNotNull(entity);
        assertNotNull(entity.getRowId());

        EntityDO notFound = (EntityDO) entityDataService.read(123);
        assertNull(notFound);

        Integer nullId = null;
        EntityDO failure = (EntityDO) entityDataService.read(nullId);
        assertNull(failure);
	}

	public void testDelete(Integer entityRowId) throws Exception {
        boolean success = entityDataService.delete(entityRowId);
        assertTrue(success);

        boolean failure = entityDataService.delete(null);
        assertFalse(failure);
	}

    public void testEmpty() {
        List<EntityDO> entities = entityDataService.read(new HashMap<String, String>());
		assertNotNull(entities);
		assertTrue(entities.isEmpty());
    }
}
