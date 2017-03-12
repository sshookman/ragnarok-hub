package codepoet.ragnarok.hub.controller;

import codepoet.ragnarok.hub.model.Request;
import java.util.Map;

public interface Controller {

	public Map<String, Object> run(final Request request);
}
