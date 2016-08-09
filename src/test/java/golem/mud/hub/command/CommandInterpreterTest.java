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

public class CommandInterpreterTest {

    private static TelnetSession session;

    @BeforeClass
    public static void setup() {
        session = mock(TelnetSession.class);

        when(session.getRenderer()).thenReturn(null);
    }

    @Test
    public void testGetNullCommand() {
        assertNull(CommandInterpreter.getCommand(null, ""));
        assertNull(CommandInterpreter.getCommand(session, null));
        assertNull(CommandInterpreter.getCommand(null, null));
        assertNull(CommandInterpreter.getCommand(session, ""));
    }

    @Test
    public void testGetAuthorCommand() {

        CommandInterface authorCommand = CommandInterpreter.getCommand(session, "author");
        assertNotNull(authorCommand);
        assertTrue("Commands starting with 'author' should be AuthorCommands", authorCommand instanceof AuthorCommand);

        CommandInterface authorCommandCaps = CommandInterpreter.getCommand(session, "AUTHOR");
        assertNotNull(authorCommandCaps);
        assertTrue("Commands starting with 'AUTHOR' should be AuthorCommands", authorCommandCaps instanceof AuthorCommand);
    }

    @Test
    public void testGetConfigCommand() {

        CommandInterface configCommand = CommandInterpreter.getCommand(session, "config");
        assertNotNull(configCommand);
        assertTrue("Commands starting with 'config' should be ConfigCommands", configCommand instanceof ConfigCommand);

        CommandInterface configCommandCaps = CommandInterpreter.getCommand(session, "CONFIG");
        assertNotNull(configCommandCaps);
        assertTrue("Commands starting with 'CONFIG' should be ConfigCommands", configCommandCaps instanceof ConfigCommand);
    }

    @Test
    public void testGetHelpCommand() {

        CommandInterface helpCommand = CommandInterpreter.getCommand(session, "help");
        assertNotNull(helpCommand);
        assertTrue("Commands starting with 'help' should be HelpCommands", helpCommand instanceof HelpCommand);

        CommandInterface helpCommandCaps = CommandInterpreter.getCommand(session, "HELP");
        assertNotNull(helpCommandCaps);
        assertTrue("Commands starting with 'HELP' should be HelpCommands", helpCommandCaps instanceof HelpCommand);
    }

    @Test
    public void testGetStoryCommand() {

        CommandInterface storyCommand = CommandInterpreter.getCommand(session, "story");
        assertNotNull(storyCommand);
        assertTrue("Commands starting with 'story' should be StoryCommands", storyCommand instanceof StoryCommand);

        CommandInterface storyCommandCaps = CommandInterpreter.getCommand(session, "STORY");
        assertNotNull(storyCommandCaps);
        assertTrue("Commands starting with 'STORY' should be StoryCommands", storyCommandCaps instanceof StoryCommand);
    }
}
