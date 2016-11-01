package golem.mud;

import golem.mud.common.telnet.TelnetServer;

public class Golem {

	public static void main(String[] args) {
		new TelnetServer(args.length == 0 ? "1127" : args[0]).run();
	}
}
