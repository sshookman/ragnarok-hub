package client;

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
	private PrintWriter writer;
	private BufferedReader reader;

    public Client(final Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("Welcome to Dragonfly Online!");
			login();

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

	public void login() throws IOException {

		writer.write("Login > ");
		writer.flush();
		String input = reader.readLine();
		writer.println("Welcome " + input);
	}
}

