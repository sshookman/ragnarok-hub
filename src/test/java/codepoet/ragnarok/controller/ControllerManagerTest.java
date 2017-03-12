package codepoet.ragnarok.controller;

import codepoet.ragnarok.hub.controller.Controller;
import codepoet.ragnarok.hub.controller.ControllerManager;
import codepoet.ragnarok.hub.controller.LibraryController;
import codepoet.ragnarok.hub.controller.WelcomeController;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ControllerManagerTest {

	@Mock
	private WelcomeController welcomeController;

	@Mock
	private LibraryController libraryController;

	@InjectMocks
	private ControllerManager controllerManager;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testResolve() {
		Controller welcome = controllerManager.resolve("welcome");
		assertNotNull(welcome);
		assertTrue(welcome instanceof WelcomeController);

		Controller library = controllerManager.resolve("library");
		assertNotNull(library);
		assertTrue(library instanceof LibraryController);

		assertNull(controllerManager.resolve(""));
		assertNull(controllerManager.resolve(null));
	}
}
