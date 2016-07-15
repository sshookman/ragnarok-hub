import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {
	private final Logger logger = Logger.getLogger(Client.class.getName());

    private final Socket socket;

    public Client(final Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Welcome to the Server!");
			socket.close();

        } catch (IOException e) {
            logger.info("EXIT");
			try {
				socket.close();
			} catch (IOException exception) {
				logger.info("Failed to close socket");
			}
		}
    }
}

