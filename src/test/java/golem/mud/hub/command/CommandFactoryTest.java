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

import golem.mud.hub.exception.CommandException;
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
    public void testGetException() {
        try {
            commandFactory.buildCommand(null);
        } catch (CommandException exception) {
            assertNotNull(exception);
            assertEquals("Expecting Invalid Error Message", "Invalid Command/Session", exception.getMessage());
            assertNull(exception.getCommand());
        }

        try {
            commandFactory.buildCommand("");
        } catch (CommandException exception) {
            assertNotNull(exception);
            assertEquals("Expecting Not Found Error Message", "Command Not Found", exception.getMessage());
            assertEquals("Expecting Empty Command String", "", exception.getCommand());
        }

        try {
            commandFactory.buildCommand("This is not a command");
        } catch (CommandException exception) {
            assertNotNull(exception);
            assertEquals("Expecting Not Found Error Message", "Command Not Found", exception.getMessage());
            assertEquals("Expecting Command String", "This is not a command", exception.getCommand());
        }
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

    @Test
    public void testGetStoryListCommand() {

        CommandInterface storyCommand = commandFactory.buildCommand("story ls");
        assertNotNull(storyCommand);
        assertTrue("Commands starting with 'story' should be StoryCommands", storyCommand instanceof StoryListCommand);

        CommandInterface storyCommandCaps = commandFactory.buildCommand("STORY LIST");
        assertNotNull(storyCommandCaps);
        assertTrue("Commands starting with 'STORY' should be StoryCommands", storyCommandCaps instanceof StoryListCommand);
    }

    @Test
    public void testGetCommandListCommand() {

        CommandInterface commandCommand = commandFactory.buildCommand("command ls");
        assertNotNull(commandCommand);
        assertTrue("Commands starting with 'command' should be CommandCommands", commandCommand instanceof CommandListCommand);

        CommandInterface commandCommandCaps = commandFactory.buildCommand("COMMAND LIST");
        assertNotNull(commandCommandCaps);
        assertTrue("Commands starting with 'COMMAND' should be CommandCommands", commandCommandCaps instanceof CommandListCommand);
    }
}
