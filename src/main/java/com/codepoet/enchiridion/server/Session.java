package com.codepoet.enchiridion.server;

import com.codepoet.enchiridion.render.Renderer;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Session {

	private static final Logger LOGGER = Logger.getLogger(Session.class.getName());

	private String id;
	private final Socket socket;
	private final Renderer renderer;

	protected Session(final Socket socket) throws Exception {
		this.id = new BigInteger(130, new SecureRandom()).toString(32);
		this.socket = socket;
		this.renderer = new Renderer(socket);
	}

	public String getId() {
		return id;
	}

	public Renderer getRenderer() {
		return this.renderer;
	}

	public boolean isOpen() {
		return socket != null && !socket.isClosed();
	}

	public void close() {
		try {
			renderer.endl(1);
			renderer.write("Thanks for Playing!");
			renderer.endl(1);
			socket.close();
		} catch (IOException exception) {
			LOGGER.log(Level.SEVERE, "Failed to Close Session: {0}", exception.getMessage());
		}
	}
}
