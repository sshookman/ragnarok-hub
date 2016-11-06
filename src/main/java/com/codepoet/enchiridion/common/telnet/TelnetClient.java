package com.codepoet.enchiridion.common.telnet;

import com.codepoet.enchiridion.hub.EnchiridionHub;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelnetClient implements Runnable {

	private final static Logger LOGGER = Logger.getLogger(TelnetClient.class.getName());

	private final EnchiridionHub hub;
	private final String sessionId;

	public TelnetClient(final EnchiridionHub hub, final String sessionId) throws Exception {
		this.hub = hub;
		this.sessionId = sessionId;
	}

	@Override
	public void run() {
		try {
			hub.start(sessionId);
		} catch (Exception exception) {
			LOGGER.log(Level.SEVERE, exception.getMessage());
		} finally {
			LOGGER.log(Level.INFO, "Close Session");
		}
	}
}
