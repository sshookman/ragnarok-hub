package com.codepoet.enchiridion.common.telnet;

import com.codepoet.enchiridion.hub.controller.HubControllers;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TelnetServer {

	private static final Logger LOGGER = Logger.getLogger(TelnetServer.class.getName());
	private static final int PORT = 1127;

	private final TelnetSessionManager sessionManager;
	private final HubControllers hubControllers;
	private final ExecutorService executor = Executors.newFixedThreadPool(5);
	private ServerSocket server = null;

	@Autowired
	public TelnetServer(final TelnetSessionManager sessionManager, final HubControllers hubControllers) {
		this.sessionManager = sessionManager;
		this.hubControllers = hubControllers;
		start();
	}

	private void start() {
		try {
			server = new ServerSocket(PORT);
			LOGGER.log(Level.INFO, "Server Listening on Port : {0}", PORT);
			listen();
		} catch (Exception exception) {
			LOGGER.log(Level.WARNING, "Shutting Down Due To {0}", exception.getMessage());
			executor.shutdown();
		}
	}

	private void listen() throws Exception {
		while (true) {
			Socket socket = server.accept();
			TelnetSession session = TelnetSession.instance(socket);
			TelnetClient client = new TelnetClient(hubControllers, session);

			sessionManager.addSession(session);
			executor.execute(client);
			LOGGER.log(Level.INFO, "Opening Session {0}", session.getId());

			sessionManager.verifySessions();
		}
	}

}
