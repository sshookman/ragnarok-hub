package golem.mud.hub.telnet;

import java.sql.Connection;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.logging.Level;

import golem.mud.hub.util.SocketUtil;
import golem.mud.hub.das.ConnectionManager;

public class TelnetSession {
	private static final Logger LOGGER = Logger.getLogger(TelnetSession.class.getName());
    private static final String HUB_DB_PATH = "server/GOLEM.gmh";

    private final Socket socket;
	private final TelnetRenderer renderer;
    private final Connection connection;

    protected TelnetSession(final Socket socket) throws Exception {
        this.socket = socket;
        this.renderer = new TelnetRenderer(SocketUtil.getReader(socket), SocketUtil.getWriter(socket));
        this.connection = ConnectionManager.establishConnection(HUB_DB_PATH);
    }

    public TelnetRenderer getRenderer() {
        return this.renderer;
    }

    public Connection getConnection() {
        return this.connection;
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
