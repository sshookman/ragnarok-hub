package golem.mud.hub.telnet;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.logging.Level;

import golem.mud.hub.util.SocketUtil;

public class TelnetSession {
	private final static Logger LOGGER = Logger.getLogger(TelnetSession.class.getName());

    private final Socket socket;
	private final TelnetRenderer renderer;

    protected TelnetSession(final Socket socket) throws IOException {
        this.socket = socket;
        this.renderer = new TelnetRenderer(SocketUtil.getReader(socket), SocketUtil.getWriter(socket));
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

    public static TelnetSession instance(final Socket socket) {
        try {
            return new TelnetSession(socket);
        } catch (Exception exception) {
            LOGGER.log(Level.WARNING, "Failed to create context");
            return null;
        }
    }
}
