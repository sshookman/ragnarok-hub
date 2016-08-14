package golem.mud.hub.das;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.sql.Connection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import golem.mud.hub.das.ConnectionManager;
import golem.mud.hub.das.model.AuthorDO;

public class AuthorDataServiceTest {

    private static Connection golemMudHub;
	private static AuthorDataService authorDataService;

	@BeforeClass
	public static void setup() throws Exception {
		golemMudHub = ConnectionManager.establishConnection("test/GolemMudHubTest.gmh");
		golemMudHub = ConnectionManager.initGolemMudHub(golemMudHub);
		authorDataService = new AuthorDataService(golemMudHub);
	}

    @AfterClass
    public static void teardown() throws Exception {
        golemMudHub = ConnectionManager.initGolemMudHub(golemMudHub);
    }

	@Test 
	public void testAuthorDataService() throws Exception {
		testCreate();
		int authorRowId = testReadSearch();
		testUpdate(authorRowId);
        testRead(authorRowId);
        testDelete(authorRowId);
        testEmpty();
	}

	public void testCreate() throws Exception {
		AuthorDO author = new AuthorDO();
		author.setUsername("Sean");
		boolean success = authorDataService.create(author);
		assertTrue(success);

		AuthorDO badAuthor = new AuthorDO();
		boolean failure = authorDataService.create(badAuthor);
		assertFalse(failure);

		boolean superFailure = authorDataService.create(null);
		assertFalse(superFailure);
	}

	public Integer testReadSearch() throws Exception {
		Integer authorRowId;
		
		List<AuthorDO> authors = authorDataService.read(new HashMap<String, String>());
		assertNotNull(authors);
		assertFalse(authors.isEmpty());

		AuthorDO author = authors.get(0);
		assertNotNull(author);
		assertEquals("Sean", author.getUsername());
		
		authorRowId = author.getRowId();

		List<AuthorDO> authorsFiltered = authorDataService.read(author.toMap());
		assertNotNull(authorsFiltered);
		assertFalse(authorsFiltered.isEmpty());

		AuthorDO authorFiltered = authorsFiltered.get(0);
		assertNotNull(authorFiltered);
		assertEquals("Sean", authorFiltered.getUsername());

		Map<String, String> badSearch = new HashMap<String, String>();
		badSearch.put("BAD", "SEARCH");
		List<AuthorDO> authorsBadFilter= authorDataService.read(badSearch);
		assertNotNull(authorsBadFilter);
		assertTrue(authorsBadFilter.isEmpty());

		return authorRowId;
	}

	public void testUpdate(Integer authorRowId) throws Exception {
		AuthorDO author = new AuthorDO();
		author.setUsername("Link");
		boolean success = authorDataService.update(authorRowId, author);
		assertTrue(success);

		boolean failure = authorDataService.update(authorRowId, new AuthorDO());
		assertFalse(failure);

		boolean failObject = authorDataService.update(authorRowId, null);
		assertFalse(failObject);

		boolean failId = authorDataService.update(null, author);
		assertFalse(failId);
	}

	public void testRead(Integer authorRowId) throws Exception {
        AuthorDO author = (AuthorDO) authorDataService.read(authorRowId);
        assertNotNull(author);
        assertEquals("Link", author.getUsername());

        AuthorDO notFound = (AuthorDO) authorDataService.read(123);
        assertNull(notFound);

        Integer nullId = null;
        AuthorDO failure = (AuthorDO) authorDataService.read(nullId);
        assertNull(failure);
	}

	public void testDelete(Integer authorRowId) throws Exception {
        boolean success = authorDataService.delete(authorRowId);
        assertTrue(success);

        boolean failure = authorDataService.delete(null);
        assertFalse(failure);
	}

    public void testEmpty() {
        List<AuthorDO> authors = authorDataService.read(new HashMap<String, String>());
		assertNotNull(authors);
		assertTrue(authors.isEmpty());
    }
}
