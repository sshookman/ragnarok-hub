package codepoet.ragnarok.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Server stands as the starting point for the hub.
 *
 * The server maitains the SessionManager and the ClientManager and also
 * supplies the main loop that listens for incoming connections over telnet.
 */
@Component
public class Server {

	private static final Logger LOGGER = Logger.getLogger(Server.class.getName());
	private static final int PORT = 1127;

	private final SessionManager sessionManager;
	private final ClientManager clientManager;
	private final ServerSocket server;

    /**
     * Constructor for Server - Autowired Managers.
     *
     * The constructor autowires in the SessionManager and ClientManager components to
     * allow for new sessions and clients to be created in the listen loop which is
     * started after the other compenents have been wired in.
     */
	@Autowired
	public Server(final SessionManager sessionManager, final ClientManager clientManager) throws IOException {
		this.sessionManager = sessionManager;
		this.clientManager = clientManager;
		this.server = new ServerSocket(PORT);
		listen();
	}

    /**
     * Listens for incoming connections on the specified PORT number.
     * 
     * Each connection is accepted and creates a socket which is stored into
     * a session instance inside the SessionManager. A client is then launched
     * from this session where the user interaction will take place.
     */
	private void listen() throws Exception {
		try {
			LOGGER.log(Level.INFO, "Server Listening on Port : {0}", PORT);
            while (true) {
                Socket socket = server.accept();
                Session session = sessionManager.instance(socket);
                clientManager.launch(session);
                sessionManager.removeClosedSessions();
            }
		} catch (Exception exception) {
			LOGGER.log(Level.WARNING, "Shutting Down Due To {0}", exception.getMessage());
			clientManager.shutdown();
		}
	}
}
