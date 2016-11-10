package com.codepoet.enchiridion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControllerManager {

	@Autowired
	private WelcomeController welcomeController;

	public Controller resolve(String ctrl) {
		switch (ctrl) {
			case "welcome":
				return welcomeController;
			default:
				return null;
		}
	}
}
