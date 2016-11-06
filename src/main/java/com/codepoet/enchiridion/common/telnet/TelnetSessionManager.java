package com.codepoet.enchiridion.common.telnet;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class TelnetSessionManager {

	private static final Logger LOGGER = Logger.getLogger(TelnetSessionManager.class.getName());
	private Map<String, TelnetSession> sessions;

	public TelnetSessionManager() {
		sessions = new HashMap<>();
	}

	public void addSession(TelnetSession session) {
		sessions.put(session.getIdentifier(), session);
	}

	public TelnetSession getSession(String sessionId) {
		return sessions.get(sessionId);
	}

	public Map<String, TelnetSession> getSessions() {
		return sessions;
	}

	public void setSessions(final Map<String, TelnetSession> sessions) {
		this.sessions = sessions;
	}

	public void verifySessions() {
		Map<String, TelnetSession> openSessions = new HashMap<>();
		sessions.entrySet().parallelStream().forEach((entry) -> {
			if (entry.getValue().isOpen()) {
				openSessions.put(entry.getKey(), entry.getValue());
			}
		});

		LOGGER.log(Level.INFO, "Closed {0} Client Session(s)", sessions.size() - openSessions.size());
		LOGGER.log(Level.INFO, "{0} Open Client Session(s)", openSessions.size());
		sessions = openSessions;
	}
}
