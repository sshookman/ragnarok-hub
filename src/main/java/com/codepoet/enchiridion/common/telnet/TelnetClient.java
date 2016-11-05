package com.codepoet.enchiridion.common.telnet;

import java.util.logging.Logger;

public class TelnetClient implements Runnable {

	private final static Logger LOGGER = Logger.getLogger(TelnetClient.class.getName());

	private final TelnetSession session;

	public TelnetClient(final TelnetSession session) throws Exception {
		this.session = session;
	}

	@Override
	public void run() {
		try {
			TelnetMain.mainLoop(session);
		} catch (Exception exception) {
			LOGGER.severe(exception.getMessage());
			session.getRenderer().write(exception.getMessage());
			session.getRenderer().endl(1);
		} finally {
			session.closeSession();
		}
	}
}
