package codepoet.ragnarok.render;

import codepoet.ragnarok.util.SocketUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Renderer {

	private final Logger LOGGER = Logger.getLogger(Renderer.class.getName());

	private static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";

	private final BufferedReader reader;
	private final PrintWriter writer;

	public Renderer(final Socket socket) throws IOException {
		this.reader = SocketUtil.buildReader(socket);
		this.writer = SocketUtil.buildWriter(socket);
	}

	public String prompt() {
		endl();
		write(" > ");
		return read();
	}

	public String prompt(final String prompt) {
		endl();
		write(prompt + " > ");
		String input = read();
		endl();
		return input;
	}

	public String read() {
		try {
			return reader.readLine();
		} catch (IOException ex) {
			return "";
		}
	}

	public void write(final String message) {
		writer.write(message);
		writer.flush();
	}

	public void write(final String message, final String color) {
		write(color + message + RESET);
	}

	public void write(final String message, final int speed) {
		writeByLetter(message, speed);
		writer.flush();
	}

	public void write(final String message, final String color, final int speed) {
		writer.write(color);
		writeByLetter(message, speed);
		writer.write(RESET);
		writer.flush();
	}

	public void writeln(final String message) {
		write(message);
		endl(1);
	}

	public void endl() {
		endl(1);
	}

	public void endl(final Integer count) {
		for (int x = 0; x < count; x++) {
			write("\n");
		}
	}

	private void writeByLetter(final String message, final int speed) {
		for (int index = 0; index < message.length(); index++) {
			sleep(speed);
			char character = message.charAt(index);
			writer.write(character);
		}
	}

	private void sleep(final int speed) {
		try {
			Thread.sleep(speed);
		} catch (Exception exception) {
			LOGGER.log(Level.SEVERE, "Failed to Sleep Thread: {0}", exception.getMessage());
		}
	}
}
