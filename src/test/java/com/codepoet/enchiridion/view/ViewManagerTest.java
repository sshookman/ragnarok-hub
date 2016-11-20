package com.codepoet.enchiridion.view;

import com.codepoet.enchiridion.hub.view.ViewManager;
import com.codepoet.enchiridion.hub.view.WelcomeView;
import com.codepoet.enchiridion.hub.view.View;
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
