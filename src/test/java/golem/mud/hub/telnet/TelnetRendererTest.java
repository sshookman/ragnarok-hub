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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.eq;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class TelnetRendererTest {

    private static TelnetRenderer renderer;
    private static BufferedReader readerMock;
    private static PrintWriter writerMock;

    @BeforeClass
    public static void setup() throws Exception {
        readerMock = mock(BufferedReader.class);
        writerMock = mock(PrintWriter.class);
        
        doNothing().when(writerMock).flush();
        doNothing().when(writerMock).write(eq("valid"));
        doThrow(new RuntimeException("error")).when(writerMock).write(eq("invalid"));

        when(readerMock.readLine()).thenReturn("valid").thenThrow(new IOException("error"));

        renderer = new TelnetRenderer(readerMock, writerMock);
    }

    @Test
    public void testWrite() {
        renderer.write("valid");
        renderer.write("valid", 10);

        try {
            renderer.write("invalid");
            fail("Exception Expected");
        } catch (RuntimeException exception) {
            assertNotNull(exception);
            assertEquals("Expected Message to be 'error'", "error", exception.getMessage());
        }
    }

    @Test
    public void testRead() throws IOException {
        String response = renderer.read();
        assertNotNull(response);
        assertEquals("Expected to read 'valid'", "valid", response);

        try {
            renderer.read();
            fail("Exception Expected");
        } catch (IOException exception) {
            assertNotNull(exception);
            assertEquals("Expected Message to be 'error'", "error", exception.getMessage());
        }
    }

    @Test
    public void testEndl() {
        renderer.endl(5);
    }
}
