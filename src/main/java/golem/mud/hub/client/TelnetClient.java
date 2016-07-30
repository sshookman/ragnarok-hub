package golem.mud.hub.client;

import java.sql.Connection;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

import golem.mud.hub.rendering.TelnetRenderer;
import golem.mud.hub.service.MainLobbyService;
import golem.mud.hub.service.AuthenticationService;
import golem.mud.hub.util.ConnectionUtil;

public class TelnetClient implements Runnable {
	private final static Logger LOGGER = Logger.getLogger(TelnetClient.class.getName());
    private static final String HUB_DB_PATH = "server/GOLEM.gmh";

    private final Socket socket;
	private final TelnetRenderer renderer;
	private final AuthenticationService auth;
	private final MainLobbyService lobby;

    private final Connection hubDatabase;

    public TelnetClient(final Socket socket) throws IOException, Exception {
        this.socket = socket;
 		this.renderer = new TelnetRenderer(socket);
        this.hubDatabase = ConnectionUtil.establishConnection(HUB_DB_PATH);
		this.auth = new AuthenticationService(hubDatabase);
		this.lobby = new MainLobbyService(renderer);
    }

    @Override
    public void run() {

        try {
            renderer.write("================================\n");
            renderer.write("Welcome to the Dragonfly Mud-Hub\n");
            renderer.write("================================\n\n");
            renderer.write("Enter Username: ");
            String username = renderer.read();
            auth.exists(username);

			lobby.start();	

			socket.close();

        } catch (IOException e) {
            LOGGER.info("EXIT");
		} finally {
			try {
				socket.close();
			} catch (IOException exception) {
				LOGGER.info("Failed to close socket");
			}
		}
    }
}

