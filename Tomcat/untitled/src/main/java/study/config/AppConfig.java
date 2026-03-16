package study.config;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AppConfig {
    private final int port;
    private final Map<String, String> servletMappings;
    private final List<String> filters;

    public AppConfig(int port, Map<String, String> servletMappings, List<String> filters) {
        this.port = port;
        this.servletMappings = new LinkedHashMap<>(servletMappings);
        this.filters = List.copyOf(filters);
    }

    public int getPort() {
        return port;
    }

    public Map<String, String> getServletMappings() {
        return servletMappings;
    }

    public List<String> getFilters() {
        return filters;
    }
}
