package com.codepoet.enchiridion.common.telnet;

import com.codepoet.enchiridion.hub.controller.WelcomeController;

public class TelnetMain {

	public static void mainLoop(final TelnetSession session) throws Exception {
		WelcomeController welcomeCtrl = new WelcomeController(session);
		welcomeCtrl.welcome();
	}
}
