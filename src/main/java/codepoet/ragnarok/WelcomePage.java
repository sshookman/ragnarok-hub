package codepoet.ragnarok;

import codepoet.ragnarok.hub.Pageable;
import codepoet.ragnarok.hub.Route;
import codepoet.ragnarok.render.Renderer;
import codepoet.venalartificer.TemplateBuilder;
import java.util.Map;

public class WelcomePage implements Pageable {
    
    private TemplateBuilder templateBuilder;
    private Renderer renderer;

    public WelcomePage(TemplateBuilder templateBuilder, Renderer renderer) {
        this.templateBuilder = templateBuilder;
        this.renderer = renderer;
    }
    
    @Override
    public Route render(Map<String, String> params) {
        String renderText = templateBuilder.render("welcome", null);
        renderer.write(renderText);
        return null; //Should prompt and create a route
    }
}
