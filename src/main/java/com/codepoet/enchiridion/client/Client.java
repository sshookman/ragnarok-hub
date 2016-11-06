package com.codepoet.enchiridion.client;

import com.codepoet.enchiridion.hub.route.Route;
import com.codepoet.enchiridion.hub.route.RouteNames;
import com.codepoet.enchiridion.hub.route.Router;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {

	private final static Logger LOGGER = Logger.getLogger(Client.class.getName());

	private final Session session;
	private final Router router;

	public Client(final Session session) throws Exception {
		this.session = session;
		this.router = new Router(session);
	}

	@Override
	public void run() {
		try {
			inputLoop();
		} catch (Exception exception) {
			LOGGER.log(Level.SEVERE, exception.getMessage());
		} finally {
			LOGGER.log(Level.INFO, "Closing Session {0}", session.getId());
			session.closeSession();
		}
	}

	private void inputLoop() {
		Route route = new Route(RouteNames.WELCOME);
		while (route != null) {
			route = router.resolve(route);
		}
	}
}
