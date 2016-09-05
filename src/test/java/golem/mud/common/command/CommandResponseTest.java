package golem.mud.common.command;

import org.junit.Test;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

public class CommandResponseTest {

    @Test
    public void testCommandResponse() {
        CommandResponse response = new CommandResponse("DATA");
        assertNotNull(response);
        assertEquals("DATA", response.getData().toString());
        assertEquals("DATA", response.getStringData());
    }

    @Test
    public void testCommandResponseIsNullSafe() {
        CommandResponse response = new CommandResponse(null);
        assertNotNull(response);
        assertNull(response.getData());
        assertNull(response.getStringData());
    }
}
