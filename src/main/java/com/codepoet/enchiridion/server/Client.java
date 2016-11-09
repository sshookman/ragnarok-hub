package com.codepoet.enchiridion.server;

import com.codepoet.enchiridion.controller.ControllerManager;
import com.codepoet.enchiridion.view.WelcomeView;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Runnable {

	private final static Logger LOGGER = Logger.getLogger(Client.class.getName());

	private final Session session;
	private final ControllerManager controllerManager;

	public Client(final Session session, final ControllerManager controllerManager) throws Exception {
		this.session = session;
		this.controllerManager = controllerManager;
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
		Map<String, Object> model = controllerManager.welcomeController.welcome();
		WelcomeView.render(session.getRenderer(), model);
	}
}
