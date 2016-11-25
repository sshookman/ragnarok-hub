package com.codepoet.enchiridion.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.Socket;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SessionTest {

	private static Socket socketMock;
	private final SessionManager sessionManager = new SessionManager();

	@BeforeClass
	public static void setup() throws Exception {
		socketMock = mock(Socket.class);

		when(socketMock.getInputStream()).thenReturn(new ByteArrayInputStream("INPUT".getBytes()));
		when(socketMock.getOutputStream()).thenReturn(new ByteArrayOutputStream());
	}

	@Test
	public void testSession() {
		Session session = sessionManager.instance(socketMock);

		assertNotNull(session.getId());
		assertNotNull(session.getRenderer());
		assertTrue(session.isOpen());

		session.close();
	}

	@Test
	public void testSessionWithNullSocket() {
		assertNull(sessionManager.instance(null));
	}
}
