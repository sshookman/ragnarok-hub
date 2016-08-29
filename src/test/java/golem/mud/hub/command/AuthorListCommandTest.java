package golem.mud.hub.command;

import java.sql.Connection;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.anyInt;

import golem.mud.das.ConnectionManager;
import golem.mud.hub.das.AuthorDataService;
import golem.mud.hub.das.model.AuthorDO;
import golem.mud.hub.telnet.TelnetSession;
import golem.mud.hub.telnet.TelnetRenderer;

public class AuthorListCommandTest {

    private static AuthorListCommand authorListCommand;
    private static TelnetSession session;
    private static TelnetRenderer renderer;
    private static Connection connection;

    @BeforeClass
    public static void setup() throws Exception {
        session = mock(TelnetSession.class);
        renderer = mock(TelnetRenderer.class);

		connection = ConnectionManager.establishConnection("test/GolemMudHubTest.gmh");
		connection = ConnectionManager.initGolemMudHub(connection);
        AuthorDataService authorDataService = new AuthorDataService(connection);

        AuthorDO authorBob = new AuthorDO();
        authorBob.setUsername("Bob");
        AuthorDO authorJerry = new AuthorDO();
        authorJerry.setUsername("Jerry");

        authorDataService.create(authorBob);
        authorDataService.create(authorJerry);

        when(session.getRenderer()).thenReturn(renderer);
        when(session.getConnection()).thenReturn(connection);
        doNothing().when(renderer).write(anyString());
        doNothing().when(renderer).endl(anyInt());

        authorListCommand = new AuthorListCommand(session);
    }

    @AfterClass
    public static void teardown() throws Exception {
        ConnectionManager.initGolemMudHub(connection);
    }

    @Test
    public void testGetRegex() {
        String regex = authorListCommand.getRegex();
        assertNotNull(regex);
        assertEquals("AUTHOR (LS|LIST)", regex);
    }

    @Test
    public void testExecute() {
        try {
            authorListCommand.execute();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }
}
