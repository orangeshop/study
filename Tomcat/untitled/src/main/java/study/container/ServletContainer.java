package study.container;

import study.servlet.Servlet;

import java.util.HashMap;
import java.util.Map;

public class ServletContainer {
    private final Map<String, Servlet> servletMap = new HashMap<>();

    public void register(String path, Servlet servlet) {
        servlet.init();
        servletMap.put(path, servlet);
    }

    public Servlet getServlet(String path) {
        return servletMap.get(path);
    }

    public void destroyAll() {
        for (Servlet servlet : servletMap.values()) {
            servlet.destroy();
        }
    }
}
