package codepoet.ragnarok.hub;

import java.util.Map;

public interface Pageable {

	public PageData render(final Map<String, String> params);
}
