package com.codepoet.enchiridion.server;

import com.codepoet.enchiridion.controller.ControllerManager;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Server {

	private static final Logger LOGGER = Logger.getLogger(Server.class.getName());
	private static final int PORT = 1127;

	private final SessionManager sessionManager;
	private final ControllerManager controllerManager;
	private final ExecutorService executor = Executors.newFixedThreadPool(5);
	private final ServerSocket server;

	@Autowired
	public Server(final SessionManager sessionManager, final ControllerManager controllerManager) throws IOException {
		this.sessionManager = sessionManager;
		this.controllerManager = controllerManager;
		this.server = new ServerSocket(PORT);
		start();
	}

	private void start() {
		try {
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
			Session session = Session.instance(socket);
			Client client = new Client(session, controllerManager);

			sessionManager.addSession(session);
			executor.execute(client);
			LOGGER.log(Level.INFO, "Opening Session {0}", session.getId());

			sessionManager.removeClosedSessions();
		}
	}
}
