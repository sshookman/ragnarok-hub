import java.util.List;
import java.sql.Connection;

import golem.mud.hub.server.TelnetServer;

public class Main {

	public static void main(String[] args) {
		new TelnetServer(args.length == 0 ? "1127" : args[0]).run();
	}
}
