package com.codepoet.enchiridion.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControllerManager {

	private static final Logger LOGGER = Logger.getLogger(ControllerManager.class.getName());

	@Autowired
	private WelcomeController welcomeController;

	@Autowired
	private LibraryController libraryController;

	public Controller resolve(String ctrl) {
		try {
			switch (ctrl) {
				case "welcome":
					return welcomeController;
				case "library":
					return libraryController;
				default:
					return null;
			}
		} catch (Exception exception) {
			LOGGER.log(Level.WARNING, "Failed to Resolve Controller: {0}", exception.getMessage());
			return null;
		}
	}
}
