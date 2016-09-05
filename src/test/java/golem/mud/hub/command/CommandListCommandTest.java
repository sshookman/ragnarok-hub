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

import golem.mud.common.das.ConnectionManager;
import golem.mud.hub.das.StoryDataService;
import golem.mud.hub.das.model.StoryDO;
import golem.mud.hub.telnet.TelnetSession;
import golem.mud.hub.telnet.TelnetRenderer;

public class CommandListCommandTest {

    private static CommandListCommand commandListCommand;
    private static TelnetSession session;
    private static TelnetRenderer renderer;
    private static Connection connection;

    @BeforeClass
    public static void setup() throws Exception {
        session = mock(TelnetSession.class);
        renderer = mock(TelnetRenderer.class);

        when(session.getRenderer()).thenReturn(renderer);
        doNothing().when(renderer).write(anyString());
        doNothing().when(renderer).endl(anyInt());

        commandListCommand = new CommandListCommand(session);
    }

    @Test
    public void testGetRegex() {
        String regex = commandListCommand.getRegex();
        assertNotNull(regex);
        assertEquals("COMMAND (LS|LIST)", regex);
    }

    @Test
    public void testExecute() {
        try {
            commandListCommand.execute();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }
}
