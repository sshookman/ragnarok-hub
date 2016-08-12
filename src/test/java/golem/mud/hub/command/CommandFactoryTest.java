package golem.mud.hub.command;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;

import golem.mud.hub.telnet.TelnetSession;

public class CommandFactoryTest {

    private static TelnetSession session;
    private static CommandFactory commandFactory;

    @BeforeClass
    public static void setup() {
        session = mock(TelnetSession.class);
        commandFactory = new CommandFactory(session);
        when(session.getRenderer()).thenReturn(null);
    }

    @Test
    public void testGetNullCommand() {
        assertNull(commandFactory.buildCommand(""));
        assertNull(commandFactory.buildCommand(null));
    }

    @Test
    public void testGetAuthorListCommand() {

        CommandInterface authorCommand = commandFactory.buildCommand("author ls");
        assertNotNull(authorCommand);
        assertTrue("Commands starting with 'author' should be AuthorCommands", authorCommand instanceof AuthorListCommand);

        CommandInterface authorCommandCaps = commandFactory.buildCommand("AUTHOR LIST");
        assertNotNull(authorCommandCaps);
        assertTrue("Commands starting with 'AUTHOR' should be AuthorCommands", authorCommandCaps instanceof AuthorListCommand);
    }
}
