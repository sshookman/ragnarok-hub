package golem.mud.hub.view;

import golem.mud.common.telnet.TelnetRenderer;
import golem.mud.common.telnet.TelnetSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WelcomeViewTest {

	private static TelnetRenderer mockRenderer;
	private static TelnetSession mockSession;
	private static WelcomeView welcomeView;

	@BeforeClass
	public static void setup() {
		mockSession = mock(TelnetSession.class);
		mockRenderer = mock(TelnetRenderer.class);

		when(mockSession.getRenderer()).thenReturn(mockRenderer);

		welcomeView = new WelcomeView(mockSession);
	}

	@Test
	public void testWelcome() {
		try {
			welcomeView.welcome(null);
		} catch (Exception exception) {
			fail("Exception not expected: " + exception.getMessage());
		}
	}

	@Test
	public void testBuildWelcomeView() {
		try {
			String optionlessWelcome = welcomeView.buildWelcomeView(null);
			assertNotNull(optionlessWelcome);

			String optionlessEmptyWelcome = welcomeView.buildWelcomeView(new HashMap<>());
			assertNotNull(optionlessEmptyWelcome);

			List<String> options = new ArrayList<>();
			options.add("OPTION 1");
			options.add("OPTION 2");
			options.add("OPTION 3");

			Map<String, Object> model = new HashMap<>();
			model.put("options", options);

			String optionWelcome = welcomeView.buildWelcomeView(model);
			assertNotNull(optionWelcome);
			assertTrue(optionWelcome.contains("    [1] OPTION 1"));
			assertTrue(optionWelcome.contains("    [2] OPTION 2"));
			assertTrue(optionWelcome.contains("    [3] OPTION 3"));

		} catch (Exception exception) {
			fail("Exception not expected: " + exception.getMessage());
		}
	}

}
