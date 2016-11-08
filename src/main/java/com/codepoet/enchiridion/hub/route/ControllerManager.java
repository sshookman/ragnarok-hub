package com.codepoet.enchiridion.hub.route;

import com.codepoet.enchiridion.hub.screen.welcome.WelcomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControllerManager {

	@Autowired
	public WelcomeController welcomeController;
}
