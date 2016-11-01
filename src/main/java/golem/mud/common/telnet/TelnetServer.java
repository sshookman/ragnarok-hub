package golem.mud.common.telnet;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class TelnetServer {
	private final Logger logger = Logger.getLogger(TelnetServer.class.getName());

    private ServerSocket server = null;
    private final ExecutorService executor = Executors.newFixedThreadPool(5);
    private final int port;

    public TelnetServer(String port) {
        this.port = port != null ? Integer.valueOf(port).intValue() : 1127;
    }

    public void run() {

        try {
            server = new ServerSocket(port);
			logger.info("Server listening on port : " + port);

            while (true) {
                Socket socket = server.accept();
                executor.execute(new TelnetClient(socket));
            }

        } catch (Exception e) {
			executor.shutdown();
        }
    }

    public boolean isRunning() {
        return !server.isClosed();
    }

    public void shutDown() throws IOException {
        if (server != null) {
            server.close();
        }
    }
}
