package golem.mud.hub.telnet;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class TelnetClient implements Runnable {
	private final static Logger LOGGER = Logger.getLogger(TelnetClient.class.getName());

    private final TelnetSession session;

    public TelnetClient(final Socket socket) throws Exception {
        this.session = TelnetSession.instance(socket);
    }

    @Override
    public void run() {
        try {
            session.setPlayer(TelnetLogin.login(session));
            TelnetMain.mainLoop(session);
        } catch (IOException exception) {
            LOGGER.severe(exception.getMessage());
        } finally {
            session.closeSession();
        }
    }

}

