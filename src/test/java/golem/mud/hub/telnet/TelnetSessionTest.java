package golem.mud.hub.telnet;

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
import java.net.Socket;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import golem.mud.hub.das.model.PlayerDO;

public class TelnetSessionTest {

    private static Socket socketMock;

    @BeforeClass
    public static void setup() throws Exception {
        socketMock = mock(Socket.class);

        when(socketMock.getInputStream()).thenReturn(new ByteArrayInputStream("INPUT".getBytes()));
        when(socketMock.getOutputStream()).thenReturn(new ByteArrayOutputStream());
    }

    @Test
    public void testSession() {
        TelnetSession session = TelnetSession.instance(socketMock);
        PlayerDO player = new PlayerDO();
        player.setUsername("NAME");
        session.setPlayer(player);
        PlayerDO getPlayer = session.getPlayer();

        assertNotNull(getPlayer);
        assertEquals("NAME", getPlayer.getUsername());
        assertNull(getPlayer.getPassword());
        assertNotNull(session.getRenderer());
        assertNotNull(session.getConnection());

        session.closeSession();
    }

    @Test
    public void testSessionWithNullSocket() {
        assertNull(TelnetSession.instance(null));
    }
}
