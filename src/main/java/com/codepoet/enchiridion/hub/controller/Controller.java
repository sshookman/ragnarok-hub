package com.codepoet.enchiridion.hub.controller;

import com.codepoet.enchiridion.hub.model.Request;
import java.util.Map;

public interface Controller {

	public Map<String, Object> run(final Request request);
}
