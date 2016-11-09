package com.codepoet.enchiridion.controller;

import com.codepoet.enchiridion.controller.WelcomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ControllerManager {

	@Autowired
	public WelcomeController welcomeController;
}
