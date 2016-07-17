package services;

import java.io.IOException;

import rendering.TelnetRenderer;

public class AuthenticationService implements ServiceInterface {

	private final TelnetRenderer renderer;

	public AuthenticationService(final TelnetRenderer renderer) {
		this.renderer = renderer;
	}

	public void start() throws IOException {
		renderer.write("Login as: ");
		String username = renderer.read();
		
		renderer.write("Welcome " + username + "!\n");
	}
}
