package com.codepoet.enchiridion.view;

import com.codepoet.enchiridion.render.Renderer;
import java.util.Map;

public interface View {

	public String render(final Renderer renderer, Map<String, Object> model);
}
