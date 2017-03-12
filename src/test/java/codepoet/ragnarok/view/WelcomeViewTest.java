package codepoet.ragnarok.view;

import codepoet.ragnarok.hub.model.Request;
import codepoet.ragnarok.hub.view.WelcomeView;
import codepoet.ragnarok.render.Renderer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

public class WelcomeViewTest {

	private final WelcomeView welcomeView = new WelcomeView();

	private static Renderer renderer;
	private static Map<String, Object> options;

	@BeforeClass
	public static void setup() {
		List<String> optionList = new ArrayList<>();
		optionList.add("ONE");
		optionList.add("TWO");
		optionList.add("THREE");
		optionList.add("FOUR");

		options = new HashMap<>();
		options.put("options", optionList);

		renderer = Mockito.mock(Renderer.class);

		Mockito.doNothing().when(renderer).write(Mockito.anyString());
		Mockito.when(renderer.prompt()).thenReturn("1");
	}

	@Test
	public void testRender() {
		Request request = welcomeView.render(renderer, options);
		assertNotNull(request);
		assertEquals("library", request.getController());
	}

	@Test
	public void testBuildWelcomeView() {
		String fullView = welcomeView.buildWelcomeView(options);
		assertNotNull(fullView);
		assertTrue(fullView.contains("    [1] ONE\n"));
		assertTrue(fullView.contains("    [2] TWO\n"));
		assertTrue(fullView.contains("    [3] THREE\n"));
		assertTrue(fullView.contains("    [4] FOUR\n"));

		Map<String, Object> optionless = new HashMap<>();
		optionless.put("options", 123);
		String notListView = welcomeView.buildWelcomeView(optionless);
		assertNotNull(notListView);
		assertFalse(notListView.contains("    [1] ONE\n"));
		assertFalse(notListView.contains("    [2] TWO\n"));
		assertFalse(notListView.contains("    [3] THREE\n"));
		assertFalse(notListView.contains("    [4] FOUR\n"));
		assertFalse(notListView.contains("{{OPTIONS}}"));

		String emptyView = welcomeView.buildWelcomeView(new HashMap());
		assertNotNull(emptyView);
		assertFalse(emptyView.contains("    [1] ONE\n"));
		assertFalse(emptyView.contains("    [2] TWO\n"));
		assertFalse(emptyView.contains("    [3] THREE\n"));
		assertFalse(emptyView.contains("    [4] FOUR\n"));
		assertFalse(emptyView.contains("{{OPTIONS}}"));

		String emptyViewNull = welcomeView.buildWelcomeView(null);
		assertNotNull(emptyViewNull);
		assertFalse(emptyViewNull.contains("    [1] ONE\n"));
		assertFalse(emptyViewNull.contains("    [2] TWO\n"));
		assertFalse(emptyViewNull.contains("    [3] THREE\n"));
		assertFalse(emptyViewNull.contains("    [4] FOUR\n"));
		assertFalse(emptyViewNull.contains("{{OPTIONS}}"));
	}
}
