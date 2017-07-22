package codepoet.ragnarok.server;

import codepoet.ragnarok.hub.PageRouter;
import codepoet.ragnarok.hub.Route;
import codepoet.ragnarok.hub.controller.ControllerManager;
import codepoet.venalartificer.TemplateBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {

	private final static Logger LOGGER = Logger.getLogger(Client.class.getName());

	private final Session session;
    private final PageRouter pageRouter;

	public Client(final Session session, final ControllerManager controllerManager) throws Exception {
		this.session = session;
        this.pageRouter = new PageRouter(new TemplateBuilder("templates"), session.getRenderer());
	}

	@Override
	public void run() {
		try {
            
            Route route = new Route.Builder("welcome").build();
            do {
                route = pageRouter.route(route);
            } while (route != null);
            
		} catch (Exception exception) {
			LOGGER.log(Level.SEVERE, exception.getMessage());
		} finally {
			LOGGER.log(Level.INFO, "Closing Session {0}", session.getId());
			session.close();
		}
	}
}
