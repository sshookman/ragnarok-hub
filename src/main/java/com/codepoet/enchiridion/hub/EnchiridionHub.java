package com.codepoet.enchiridion.hub;

import com.codepoet.enchiridion.hub.controller.WelcomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnchiridionHub {

	@Autowired
	private WelcomeController welcomeCtrl;

	public void start(final String sessionId) {
		welcomeCtrl.welcome(sessionId);
	}
}
