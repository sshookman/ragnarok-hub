//TODO: Merge this with current project to access through Telnet
public class Main {

	public static void main(String[] args) {
		new TelnetServer(args.length == 0 ? "1127" : args[0]).run();
	}
}
