package rendering;

import java.io.*;
import java.util.Scanner;

public class TerminalRenderer {
	
	private static final Scanner SCANNER = new Scanner(System.in);

	public TerminalRenderer() {
		SCANNER.useDelimiter("\n");
	}

	public void write(String message) {
		System.out.print(message);
	}

	public String read() {
		return SCANNER.next();	
	}
}
