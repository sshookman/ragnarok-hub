package codepoet.ragnarok.hub;

import codepoet.ragnarok.WelcomePage;
import codepoet.ragnarok.render.Renderer;
import codepoet.venalartificer.TemplateBuilder;
import java.util.HashMap;
import java.util.Map;

public class PageRouter {

    private final Map<String, Pageable> pages;

    public PageRouter(TemplateBuilder templateBuilder, Renderer renderer) {
       this.pages = new HashMap<>();
       this.pages.put("welcome", new WelcomePage(templateBuilder, renderer));
    }
    
    public Route route(final Route route) {
        return pages.get(route.getName()).render(route.getParams());
    }
}
