package com.codepoet.enchiridion.hub.view;

import com.codepoet.enchiridion.hub.model.Request;
import com.codepoet.enchiridion.render.Renderer;
import java.util.Map;

public interface View {

	public Request render(final Renderer renderer, Map<String, Object> model);
}
