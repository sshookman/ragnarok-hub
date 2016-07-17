package golem.mud.hub.rendering;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelnetRenderer {
	private final Logger logger = Logger.getLogger(TelnetRenderer.class.getName());

	private int writeSpeed = 50;
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

	public String read() throws IOException {
		return reader.readLine();
	}

	private void sleep() {
		try {
			Thread.sleep(writeSpeed);
		} catch (Exception exception) {
			return;
		}
	}
}
