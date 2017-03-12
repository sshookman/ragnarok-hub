package codepoet.ragnarok.view;

import codepoet.ragnarok.hub.view.View;
import codepoet.ragnarok.hub.view.ViewManager;
import codepoet.ragnarok.hub.view.WelcomeView;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ViewManagerTest {

	@Test
	public void testResolve() {
		ViewManager viewManager = new ViewManager();

		View welcome = viewManager.resolve("welcome");
		assertNotNull(welcome);
		assertTrue(welcome instanceof WelcomeView);

		assertNull(viewManager.resolve(""));
		assertNull(viewManager.resolve(null));
	}
}
