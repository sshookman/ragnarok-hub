package golem.mud.hub.client;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

import golem.mud.hub.rendering.TelnetRenderer;
import golem.mud.hub.service.MainLobbyService;
import golem.mud.hub.service.AuthenticationService;

public class TelnetClient implements Runnable {
	private final Logger logger = Logger.getLogger(TelnetClient.class.getName());

    private final Socket socket;
	private final TelnetRenderer renderer;
	private final AuthenticationService auth;
	private final MainLobbyService lobby;

    public TelnetClient(final Socket socket) throws IOException {
        this.socket = socket;
		this.renderer = new TelnetRenderer(socket);
		this.auth = new AuthenticationService(renderer);
		this.lobby = new MainLobbyService(renderer);
    }

    @Override
    public void run() {

        try {
            renderer.write("Welcome to the Dragonfly Mud-Hub\n\n");

			auth.start();
			lobby.start();	

			socket.close();

        } catch (IOException e) {
            logger.info("EXIT");
		} finally {
			try {
				socket.close();
			} catch (IOException exception) {
				logger.info("Failed to close socket");
			}
		}
    }
}

