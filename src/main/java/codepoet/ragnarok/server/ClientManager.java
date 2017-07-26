package codepoet.ragnarok.server;

import codepoet.ragnarok.hub.PageRouter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientManager {

	@Autowired
	private PageRouter pageRouter;

	private final ExecutorService executor = Executors.newFixedThreadPool(5);

	public void launch(final Session session) throws Exception {
		Client client = new Client(session, pageRouter);
		executor.execute(client);
	}

	public void shutdown() {
		executor.shutdown();
	}
}
