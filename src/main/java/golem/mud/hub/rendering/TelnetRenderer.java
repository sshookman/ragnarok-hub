package golem.mud.hub.rendering;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

public class TelnetRenderer {
	private final Logger logger = Logger.getLogger(TelnetRenderer.class.getName());

	private final BufferedReader reader;
	private final PrintWriter writer;

	public TelnetRenderer(final Socket socket) throws IOException {
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writer = new PrintWriter(socket.getOutputStream(), true);
	}

	public void write(final String message) {
		writer.write(message);
		writer.flush();
	}

	public void write(final String message, final int speed) {
		for (int index = 0; index < message.length(); index++){
 			sleep(speed);
 		    char character = message.charAt(index);        
 			writer.write(character);
 		}
		writer.flush();
	}

	public String read() throws IOException {
		return reader.readLine();
	}

	private void sleep(final int speed) {
		try {
			Thread.sleep(speed);
		} catch (Exception exception) {
			logger.severe("Failed to sleep thread");
			return;
		}
	}
}
