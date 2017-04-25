package codepoet.ragnarok.server;

import codepoet.ragnarok.hub.controller.Controller;
import codepoet.ragnarok.hub.controller.ControllerManager;
import codepoet.ragnarok.hub.model.Request;
import codepoet.ragnarok.hub.view.ViewManager;
import codepoet.ragnarok.render.Renderer;
import codepoet.venalartificer.TemplateBuilder;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {

	private final static Logger LOGGER = Logger.getLogger(Client.class.getName());

	private final TemplateBuilder templateBuilder = new TemplateBuilder("templates");
	private final Session session;
	private final ControllerManager controllerManager;
	private final ViewManager viewManager;

	public Client(final Session session, final ControllerManager controllerManager) throws Exception {
		this.session = session;
		this.controllerManager = controllerManager;
		this.viewManager = new ViewManager();
	}

	@Override
	public void run() {
		try {
			mainLoop();
		} catch (Exception exception) {
			LOGGER.log(Level.SEVERE, exception.getMessage());
		} finally {
			LOGGER.log(Level.INFO, "Closing Session {0}", session.getId());
			session.close();
		}
	}

	private void mainLoop() {
		Controller controller = controllerManager.resolve("welcome");
		Request request = null;

		while (controller != null) {
			Map<String, Object> model = controller.run(request);
			String welcome = templateBuilder.render(model.get("view").toString(), model);
			session.getRenderer().write(welcome, Renderer.PURPLE);
			controller = null;
//			View view = viewManager.resolve(model.get("view").toString());
//			request = view.render(session.getRenderer(), model);
//			controller = controllerManager.resolve(request.getController());
		}
	}
}
