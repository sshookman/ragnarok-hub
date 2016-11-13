package com.codepoet.enchiridion.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

public class SessionManagerTest {

	private SessionManager sessionManager;
	private Socket socket;
	private Socket socketClosed;

	@Before
	public void setUp() throws IOException {
		sessionManager = new SessionManager();

		socket = Mockito.mock(Socket.class);
		socketClosed = Mockito.mock(Socket.class);

		when(socket.getInputStream()).thenReturn(new ByteArrayInputStream("INPUT".getBytes()));
		when(socket.getOutputStream()).thenReturn(new ByteArrayOutputStream());
		when(socket.isClosed()).thenReturn(false);

		when(socketClosed.getInputStream()).thenReturn(new ByteArrayInputStream("INPUT".getBytes()));
		when(socketClosed.getOutputStream()).thenReturn(new ByteArrayOutputStream());
		when(socketClosed.isClosed()).thenReturn(true);
	}

	@Test
	public void testAddSession() throws Exception {
		Session session = new Session(socket);
		String id = session.getId();
		sessionManager.addSession(session);

		Map<String, Session> sessions = sessionManager.getSessions();
		assertNotNull(sessions);
		assertEquals(1, sessions.size());

		Session storedSession = sessions.get(id);
		assertNotNull(storedSession);
		assertEquals(id, storedSession.getId());
	}

	@Test
	public void testSetSessions() throws Exception {
		Session sessionOne = new Session(socket);
		String idOne = sessionOne.getId();
		Session sessionTwo = new Session(socket);
		String idTwo = sessionTwo.getId();

		Map<String, Session> sessions = new HashMap<>();
		sessions.put(idOne, sessionOne);
		sessions.put(idTwo, sessionTwo);

		sessionManager.setSessions(sessions);

		Map<String, Session> storedSessions = sessionManager.getSessions();
		assertNotNull(storedSessions);
		assertEquals(2, storedSessions.size());

		Session storedSessionOne = storedSessions.get(idOne);
		assertNotNull(storedSessionOne);
		assertEquals(idOne, storedSessionOne.getId());

		Session storedSessionTwo = storedSessions.get(idTwo);
		assertNotNull(storedSessionTwo);
		assertEquals(idTwo, storedSessionTwo.getId());
	}

	@Test
	public void testRemoveClosedSessions() throws Exception {
		Session sessionOne = new Session(socketClosed);
		String idOne = sessionOne.getId();
		Session sessionTwo = new Session(socket);
		String idTwo = sessionTwo.getId();

		sessionManager.addSession(sessionOne);
		sessionManager.addSession(sessionTwo);

		Map<String, Session> storedSessions = sessionManager.getSessions();
		assertNotNull(storedSessions);
		assertEquals(2, storedSessions.size());

		Session storedSessionOne = storedSessions.get(idOne);
		assertNotNull(storedSessionOne);
		assertEquals(idOne, storedSessionOne.getId());

		Session storedSessionTwo = storedSessions.get(idTwo);
		assertNotNull(storedSessionTwo);
		assertEquals(idTwo, storedSessionTwo.getId());

		sessionManager.removeClosedSessions();

		Map<String, Session> remainingSessions = sessionManager.getSessions();
		assertNotNull(remainingSessions);
		assertEquals(1, remainingSessions.size());

		Session remainingSession = remainingSessions.get(idTwo);
		assertNotNull(remainingSession);
		assertEquals(idTwo, remainingSession.getId());
	}
}
