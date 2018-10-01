package codepoet.ragnarok.server;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Component;

/**
 * The Session Manager - Telnet Based Session creation and removal.
 *
 * The Session Manager offers functionality to instantiate new sessions
 * on a given socket and maintain the list of active sessions. It also
 * exposes a method for removing sessions after the sockt was closed.
 */
@Component
public class SessionManager {

	private static final Logger LOGGER = Logger.getLogger(SessionManager.class.getName());
	private Map<String, Session> sessions = new HashMap<>();

    /**
     * Generate a new session from a socket.
     *
     * Attempts to generate a new session instance from a socket and
     * returns null if it fails to do so.
     */
	public Session instance(final Socket socket) {
		try {
			Session session = new Session(socket);
			addSession(session);
			LOGGER.log(Level.INFO, "Opening Session {0}", session.getId());
			return session;
		} catch (Exception exception) {
			LOGGER.log(Level.SEVERE, "Failed to Create Session: {0}", exception.getMessage());
			return null;
		}
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
		setSessions(openSessions);
	}
}
