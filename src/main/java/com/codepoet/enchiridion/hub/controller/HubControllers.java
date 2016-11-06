package com.codepoet.enchiridion.hub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HubControllers {

	@Autowired
	public WelcomeController welcomeController;
}
