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
		Renderer renderer = session.getRenderer();
		try {
			Route route = new Route.Builder("welcome").build();
			String input = null;

			do {
				route.setInput(input);
				PageData page = pageRouter.route(route);

				renderer.write(page.getDisplay());
				input = renderer.prompt(page.getPrompt());

				route = (page.getRoutes().containsKey("*")) ? page.getRoutes().get("*") : page.getRoutes().get(input.toLowerCase());
			} while (route != null);

		} catch (Exception exception) {
			renderer.writeln("An error has occurred: " + exception.getMessage());
			LOGGER.log(Level.SEVERE, exception.getMessage());
		} finally {
			LOGGER.log(Level.INFO, "Closing Session {0}", session.getId());
			session.close();
		}
	}
}
