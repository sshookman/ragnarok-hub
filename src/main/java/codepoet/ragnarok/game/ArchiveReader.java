package codepoet.ragnarok.game;

import codepoet.ragnarok.render.Renderer;
import codepoet.ragnarok.server.Session;

public class ArchiveReader {

	private Session session;
	private Renderer renderer;

	public ArchiveReader(Session session) {
		this.session = session;
		this.renderer = session.getRenderer();
	}

	public void start() {
		renderer.writeln("THE DRAGONF|Y EXPERIMENTS", Renderer.PURPLE, 50);
		renderer.writeln("           |", Renderer.PURPLE, 50);
		renderer.writeln("           |", Renderer.PURPLE, 50);
		renderer.writeln("           |", Renderer.PURPLE, 50);
		renderer.prompt(session.getPlayer().getUsername());
	}
}
