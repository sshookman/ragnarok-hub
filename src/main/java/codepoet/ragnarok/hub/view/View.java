package codepoet.ragnarok.hub.view;

import codepoet.ragnarok.hub.model.Request;
import codepoet.ragnarok.render.Renderer;
import java.util.Map;

public interface View {

	public Request render(final Renderer renderer, Map<String, Object> model);
}
