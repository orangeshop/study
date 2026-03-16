package study.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class AppConfigLoader {
    private static final String SERVLET_PREFIX = "servlet.";

    public AppConfig load(String resourcePath) {
        Properties properties = new Properties();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Config file not found: " + resourcePath);
            }

            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load config file: " + resourcePath, e);
        }

        int port = Integer.parseInt(properties.getProperty("server.port", "8080"));
        Map<String, String> servletMappings = loadServletMappings(properties);
        List<String> filters = loadFilters(properties);

        return new AppConfig(port, servletMappings, filters);
    }

    private Map<String, String> loadServletMappings(Properties properties) {
        Map<String, String> servletMappings = new LinkedHashMap<>();

        properties.stringPropertyNames().stream()
                .filter(name -> name.startsWith(SERVLET_PREFIX))
                .sorted()
                .forEach(name -> {
                    String path = name.substring(SERVLET_PREFIX.length());
                    servletMappings.put(path, properties.getProperty(name));
                });

        return servletMappings;
    }

    private List<String> loadFilters(Properties properties) {
        String rawFilters = properties.getProperty("filters", "");
        if (rawFilters.isBlank()) {
            return List.of();
        }

        return Arrays.stream(rawFilters.split(","))
                .map(String::trim)
                .filter(filter -> !filter.isEmpty())
                .toList();
    }
}
