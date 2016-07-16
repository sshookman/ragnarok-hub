package rendering;

import java.io.*;
import java.util.Scanner;

//Obsolete
public class TerminalRenderer {
	
	private static final String PROMPT = "> ";
	private static final String SPACER = "\n\n";
	private static final Integer TYPE_SPEED = 50;
	private static final Scanner SCANNER = new Scanner(System.in);
	private static String player = "";

	public static void renderTyped(String message) {
		for (int i = 0; i < message.length(); i++){
			sleep();
		    char character = message.charAt(i);        
			System.out.print(character);
		}
		
	}

	public static void render(String message) {
		System.out.print(message);
	}

	public static void clear() {
		//Figure out windows console later... screw windows...
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void invalidSelection() {
		System.out.print("!!! INVALID SELECTION !!!\n");
	}

	public static String prompt() {
		SCANNER.useDelimiter("\n");
		System.out.print(SPACER + player + PROMPT);
		return SCANNER.next();	
	}

	public static void setPlayer(String newPlayer) {
		player = newPlayer;
	}

	private static void sleep() {
		try {
			Thread.sleep(TYPE_SPEED);
		} catch (Exception exception) {
			return;
		}
	}
}
