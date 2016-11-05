package com.codepoet.enchiridion.common.telnet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class TelnetServer {

	private static final Logger LOGGER = Logger.getLogger(TelnetServer.class.getName());
	private static final int PORT = 1127;

	private final ExecutorService executor = Executors.newFixedThreadPool(5);
	private ServerSocket server = null;
	private Map<String, TelnetSession> sessions;

	public TelnetServer() {
		sessions = new HashMap<>();

		try {
			server = new ServerSocket(PORT);
			LOGGER.info("Server listening on port : " + PORT);

			while (true) {
				Socket socket = server.accept();
				TelnetSession session = TelnetSession.instance(socket);
				TelnetClient client = new TelnetClient(session);

				executor.execute(client);

				sessions.put(session.getIdentifier(), session);
				LOGGER.info("Established New Client Session");

				cleanupConnections();
			}

		} catch (Exception e) {
			executor.shutdown();
		}
	}

	public boolean isRunning() {
		return (server != null && !server.isClosed());
	}

	public void shutDown() throws IOException {
		if (server != null) {
			server.close();
		}
	}

	private void cleanupConnections() {
		Map<String, TelnetSession> openSessions = new HashMap<>();
		sessions.entrySet().parallelStream().forEach((entry) -> {
			if (entry.getValue().isOpen()) {
				openSessions.put(entry.getKey(), entry.getValue());
			}
		});

		LOGGER.log(Level.INFO, "Closed {0} Client Session(s)", sessions.size() - openSessions.size());
		LOGGER.log(Level.INFO, "{0} Open Client Session(s)", openSessions.size());
		sessions = openSessions;
	}
}
