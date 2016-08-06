package golem.mud.hub.telnet;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.logging.Level;

//TODO: SEAN - Add test case
public class SessionContext {
	private final static Logger LOGGER = Logger.getLogger(SessionContext.class.getName());

    private final Socket socket;
	private final TelnetRenderer renderer;

    protected SessionContext(final Socket socket) throws IOException {
        this.socket = socket;
        this.renderer = new TelnetRenderer(socket);
    }

    public TelnetRenderer getRenderer() {
        return this.renderer;
    }

    public void closeSession() {
        try {
            socket.close();
        } catch (IOException exception) {
            LOGGER.log(Level.WARNING, "Failed to close socket");
        }
    }

    public static SessionContext instance(final Socket socket) {
        try {
            return new SessionContext(socket);
        } catch (IOException exception) {
            LOGGER.log(Level.WARNING, "Failed to create context");
            return null;
        }
    }
}
