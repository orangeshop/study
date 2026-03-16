package study.config;

import study.container.ServletContainer;
import study.servlet.Servlet;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ServletConfigLoader {
    public void registerServlets(ServletContainer servletContainer, Map<String, String> servletMappings) {
        for (Map.Entry<String, String> entry : servletMappings.entrySet()) {
            servletContainer.register(entry.getKey(), createServlet(entry.getValue()));
        }
    }

    private Servlet createServlet(String className) {
        try {
            Class<?> servletClass = Class.forName(className);
            Object instance = servletClass.getDeclaredConstructor().newInstance();

            if (!(instance instanceof Servlet servlet)) {
                throw new IllegalArgumentException("Class is not a Servlet: " + className);
            }

            return servlet;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                 | NoSuchMethodException | InvocationTargetException e) {
            throw new IllegalStateException("Failed to create servlet: " + className, e);
        }
    }
}
