package golem.mud.hub;

import golem.mud.hub.telnet.TelnetServer;

//TODO: SEAN - Start doing TDD
public class Golem {

	public static void main(String[] args) {
		new TelnetServer(args.length == 0 ? "1127" : args[0]).run();
	}
}
