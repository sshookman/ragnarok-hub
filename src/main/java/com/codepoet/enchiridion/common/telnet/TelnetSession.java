package com.codepoet.enchiridion.common.telnet;

import com.codepoet.enchiridion.common.util.SocketUtil;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelnetSession {

	private static final Logger LOGGER = Logger.getLogger(TelnetSession.class.getName());
	private static final String HUB_DB_PATH = "server/GOLEM.gmh";

	private final Socket socket;
	private final TelnetRenderer renderer;
	//private final Connection connection;
	//private PlayerDO player;

	private String identifier;

	protected TelnetSession(final Socket socket) throws Exception {
		this.socket = socket;
		this.renderer = new TelnetRenderer(SocketUtil.getReader(socket), SocketUtil.getWriter(socket));
		//this.connection = ConnectionManager.establishConnection(HUB_DB_PATH);
		this.identifier = new BigInteger(130, new SecureRandom()).toString(32);
	}

	public TelnetRenderer getRenderer() {
		return this.renderer;
	}

	public String getIdentifier() {
		return identifier;
	}

//	public Connection getConnection() {
//		return this.connection;
//	}
//
//	public PlayerDO getPlayer() {
//		return this.player;
//	}
//
//	public void setPlayer(final PlayerDO player) {
//		this.player = player;
//	}
	public boolean isOpen() {
		return socket != null && !socket.isClosed();
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
