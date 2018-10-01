package codepoet.ragnarok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Ragnarok Hub.
 *
 * A Telnet accessible hub that serves out a library of text-based games.
 */
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
