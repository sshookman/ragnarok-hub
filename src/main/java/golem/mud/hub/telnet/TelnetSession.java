package golem.mud.hub.telnet;

import java.sql.Connection;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;
import java.util.logging.Level;

import golem.mud.common.util.SocketUtil;
import golem.mud.common.das.ConnectionManager;
import golem.mud.hub.das.model.PlayerDO;

public class TelnetSession {
	private static final Logger LOGGER = Logger.getLogger(TelnetSession.class.getName());
    private static final String HUB_DB_PATH = "server/GOLEM.gmh";

    private final Socket socket;
	private final TelnetRenderer renderer;
    private final Connection connection;
    private PlayerDO player;

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

    public PlayerDO getPlayer() {
        return this.player;
    }

    public void setPlayer(final PlayerDO player) {
        this.player = player;
    }

    public void closeSession() {
        try {
            renderer.write("Thanks for Playing!\n");
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
