package com.codepoet.enchiridion.server;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {

	private static final Logger LOGGER = Logger.getLogger(SessionManager.class.getName());
	private Map<String, Session> sessions;

	public SessionManager() {
		sessions = new HashMap<>();
	}

	public void addSession(Session session) {
		sessions.put(session.getId(), session);
	}

	public Map<String, Session> getSessions() {
		return sessions;
	}

	public void setSessions(final Map<String, Session> sessions) {
		this.sessions = sessions;
	}

	public void removeClosedSessions() {
		Map<String, Session> openSessions = new HashMap<>();
		sessions.entrySet().parallelStream().forEach((entry) -> {
			if (entry.getValue().isOpen()) {
				openSessions.put(entry.getKey(), entry.getValue());
			}
		});

		LOGGER.log(Level.INFO, "Removed {0} Session(s)", sessions.size() - openSessions.size());
		LOGGER.log(Level.INFO, "Currently {0} Session(s) Remaining", openSessions.size());
		sessions = openSessions;
	}
}
