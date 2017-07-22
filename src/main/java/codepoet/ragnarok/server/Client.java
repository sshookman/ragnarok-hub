package codepoet.ragnarok.server;

import codepoet.ragnarok.hub.PageData;
import codepoet.ragnarok.hub.PageRouter;
import codepoet.ragnarok.hub.Route;
import codepoet.ragnarok.render.Renderer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {

	private final static Logger LOGGER = Logger.getLogger(Client.class.getName());

	private final Session session;
	private final PageRouter pageRouter;

	public Client(Session session, PageRouter pageRouter) {
		this.session = session;
		this.pageRouter = pageRouter;
	}

	@Override
	public void run() {
		try {
			Renderer renderer = session.getRenderer();
			Route route = new Route.Builder("welcome").build();

			do {
				PageData page = pageRouter.route(route);
				renderer.write(page.getDisplay());
				String input = renderer.prompt();
				route = page.getRoutes().get(input);
			} while (route != null);

			LOGGER.log(Level.INFO, "Exiting Gracefully");

		} catch (Exception exception) {
			LOGGER.log(Level.SEVERE, exception.getMessage());
		} finally {
			LOGGER.log(Level.INFO, "Closing Session {0}", session.getId());
			session.close();
		}
	}
}
