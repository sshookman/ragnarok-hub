package codepoet.ragnarok.hub;

import java.util.Map;

public interface Pageable {
    public Route render(final Map<String, String> params);
}
