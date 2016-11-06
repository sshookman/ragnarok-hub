package com.codepoet.enchiridion.common.telnet;

import com.codepoet.enchiridion.hub.controller.HubControllers;
import com.codepoet.enchiridion.hub.route.Route;
import com.codepoet.enchiridion.hub.route.RouteNames;
import com.codepoet.enchiridion.hub.route.Router;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelnetClient implements Runnable {

	private final static Logger LOGGER = Logger.getLogger(TelnetClient.class.getName());

	private final TelnetSession session;
	private final Router router;

	public TelnetClient(final HubControllers hubControllers, final TelnetSession session) throws Exception {
		this.session = session;
		this.router = new Router(hubControllers, session.getId());
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
