package com.codepoet.enchiridion.view;

public class ViewManager {

	private final WelcomeView welcomeView = new WelcomeView();

	public View resolve(String view) {

		switch (view) {
			case "welcome":
				return welcomeView;
			default:
				return null;
		}
	}
}
