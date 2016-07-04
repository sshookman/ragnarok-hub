package rendering;

import java.io.*;
import java.util.Scanner;

public class TerminalRenderer {
	
	private static final String PROMPT = "> ";
	private static final String SPACER = "\n\n";
	private static final Scanner SCANNER = new Scanner(System.in);
	private String player = "";

	public TerminalRenderer() {
		SCANNER.useDelimiter("\n");
	}

	public void render(String message) {
		System.out.print(message);
	}

	public String prompt() {
		System.out.print(SPACER + player + PROMPT);
		return SCANNER.next();	
	}

	public void setPlayer(String player) {
		this.player = player;
	}
}
