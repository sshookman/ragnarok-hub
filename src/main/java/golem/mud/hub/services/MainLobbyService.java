package golem.mud.hub.services;

import java.io.IOException;

import golem.mud.hub.rendering.TelnetRenderer;

public class MainLobbyService implements ServiceInterface {

	private final TelnetRenderer renderer;
	
	public MainLobbyService(final TelnetRenderer renderer) {
		this.renderer = renderer;
	}

	public void start() throws IOException {
		renderer.write("+-------------------------------+\n");
		renderer.write("|       Dragonfly Mud-Hub       |\n");
		renderer.write("+-------------------------------+\n");
		
		String input = "";
		while(!"exit".equalsIgnoreCase(input)) {
			renderer.write("\n > ");
			input = renderer.read();
		}
	}	
}
