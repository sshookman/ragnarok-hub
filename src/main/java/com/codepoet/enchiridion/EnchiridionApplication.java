package com.codepoet.enchiridion;

import com.codepoet.enchiridion.common.telnet.TelnetServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnchiridionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnchiridionApplication.class, args);
		new TelnetServer(args.length == 0 ? "1127" : args[0]).run();
	}
}
