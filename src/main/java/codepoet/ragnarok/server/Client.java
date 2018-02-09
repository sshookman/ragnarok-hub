package codepoet.ragnarok.server;

import codepoet.ragnarok.exception.HubException;
import codepoet.ragnarok.game.ArchiveReader;
import codepoet.ragnarok.hub.PageData;
import codepoet.ragnarok.hub.PageRouter;
import codepoet.ragnarok.hub.Route;
import codepoet.ragnarok.model.PlayerDO;
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
			PlayerDO player = login(renderer);
			session.setPlayer(player);

			Route route = new Route.Builder("home").build();
			String input = null;

			do {
				route.setInput(input);
				PageData page = pageRouter.route(route);

				renderer.write(page.getDisplay());
				input = renderer.prompt(player.getUsername());

				Route newRoute = page.getRoutes().get(input.toLowerCase());
				if (newRoute == null) {
					renderer.writeln("Invalid Command", Renderer.RED, 50);
					Thread.sleep(1000);
					renderer.endl();
				} else if (newRoute.getName().equalsIgnoreCase("PLAY")) {
					ArchiveReader reader = new ArchiveReader(session);
					reader.start();
				} else {
					route = newRoute;
				}

			} while (!route.getName().equalsIgnoreCase("EXIT"));

		} catch (Exception exception) {
			renderer.writeln("An error has occurred: " + exception.getMessage(), Renderer.RED);
			LOGGER.log(Level.SEVERE, exception.getMessage());
		} finally {
			LOGGER.log(Level.INFO, "Closing Session {0}", session.getId());
			session.close();
		}
	}

	private PlayerDO login(Renderer renderer) throws Exception {
		PlayerDO player = new PlayerDO();
        renderer.writeln("Welcome to the Archives of Ragnarok: A Library of Adventures");
		player.setUsername(renderer.prompt("Enter your username"));
		player.setPassword(renderer.prompt("Enter your password"));
		int status = pageRouter.login(player.getUsername(), player.getPassword());

		switch (status) {
			case 0:
				String create = renderer.prompt("No account found for " + player.getUsername() + " would you like to create one? (y/n)");
				if (create.equalsIgnoreCase("y")) {
					register(player.getUsername(), player.getPassword(), renderer);
				} else {
					return login(renderer);
				}
				break;
			case -1:
				renderer.writeln("Invalid Password!", Renderer.RED, 50);
				Thread.sleep(1000);
				return login(renderer);
			default:
				renderer.writeln("Login Successful!", Renderer.GREEN, 50);
				Thread.sleep(1000);
				renderer.endl();
				break;
		}

		return player;
	}

	private void register(String username, String password, Renderer renderer) throws Exception, HubException {
		boolean isRegistered = pageRouter.register(username, password);

		if (isRegistered) {
			renderer.writeln("Account Created!", Renderer.GREEN, 50);
			Thread.sleep(1000);
			renderer.endl();
		} else {
			throw new HubException("Failed to Create Account");
		}
	}
}
