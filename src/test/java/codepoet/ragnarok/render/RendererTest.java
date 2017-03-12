package codepoet.ragnarok.render;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RendererTest {

	private Renderer renderer;
	private Socket socketMock;
	private InputStream iStream;
	private OutputStream oStream;

	@Before
	public void setup() throws Exception {
		socketMock = mock(Socket.class);

		iStream = new ByteArrayInputStream("1\n2\n".getBytes());
		oStream = new ByteArrayOutputStream();

		when(socketMock.getInputStream()).thenReturn(iStream);
		when(socketMock.getOutputStream()).thenReturn(oStream);

		renderer = new Renderer(socketMock);
	}

	@Test
	public void testWrite() {
		renderer.write("valid");
		assertEquals("valid", oStream.toString());

		renderer.write("valid", 10);
		assertEquals("validvalid", oStream.toString());

		renderer.write("valid", Renderer.PURPLE);
		assertEquals("validvalid\u001B[35mvalid\u001B[0m", oStream.toString());

		renderer.write("valid", Renderer.RED, 20);
		assertEquals("validvalid\u001B[35mvalid\u001B[0m\u001B[31mvalid\u001B[0m", oStream.toString());
	}

	@Test
	public void testPrompt() throws IOException {
		String response1 = renderer.prompt();
		assertNotNull(response1);
		assertEquals("1", response1);
		assertEquals(" > ", oStream.toString());

		String response2 = renderer.prompt();
		assertNotNull(response2);
		assertEquals("2", response2);
		assertEquals(" >  > ", oStream.toString());
	}

	@Test
	public void testRead() throws IOException {
		String response1 = renderer.read();
		assertNotNull(response1);
		assertEquals("1", response1);

		String response2 = renderer.read();
		assertNotNull(response2);
		assertEquals("2", response2);

		String response3 = renderer.read();
		assertNull(response3);
	}

	@Test
	public void testEndl() {
		renderer.endl(5);
		assertEquals("\n\n\n\n\n", oStream.toString());
	}
}
