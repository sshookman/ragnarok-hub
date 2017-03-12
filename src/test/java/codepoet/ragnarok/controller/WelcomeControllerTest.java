package codepoet.ragnarok.controller;

import codepoet.ragnarok.hub.controller.WelcomeController;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class WelcomeControllerTest {

	private WelcomeController WelcomeController;

	@Before
	public void setUp() {
		WelcomeController = new WelcomeController();
	}

	@Test
	public void testRun() {
		Map<String, Object> model = WelcomeController.run(null);
		assertNotNull(model);
		assertEquals("welcome", model.get("view").toString());

		List<String> options = (List<String>) model.get("options");
		assertNotNull(options);

		for (String option : options) {
			boolean isMatch
					= option.equals("Library") || option.equals("Authors")
					|| option.equals("Stories") || option.equals("Config");

			assertTrue(isMatch);
		}
	}
}
