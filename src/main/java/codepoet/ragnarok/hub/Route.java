package codepoet.ragnarok.hub;

import java.util.HashMap;
import java.util.Map;

public class Route {

    private String name;
    private Map<String, String> params;

    private Route(String name, Map<String, String> params) {
        this.name = name;
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public static class Builder {
    
        private String name;
        private Map<String, String> params;
        
        public Builder(String name) {
            this.name = name;
            this.params = new HashMap<>();
        }
        
        public Builder param(String key, String value) {
            params.put(key, value);
            return this;
        }
        
        public Route build() {
            return new Route(name, params);
        }
    }
}
