package study.config;

import study.filter.Filter;
import study.filter.LoggingFilter;
import study.filter.SessionFilter;
import study.session.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class FilterConfigFactory {
    private final SessionManager sessionManager;

    public FilterConfigFactory(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public List<Filter> createFilters(List<String> filterNames) {
        List<Filter> filters = new ArrayList<>();

        for (String filterName : filterNames) {
            filters.add(createFilter(filterName));
        }

        return filters;
    }

    private Filter createFilter(String filterName) {
        return switch (filterName) {
            case "session" -> new SessionFilter(sessionManager);
            case "logging" -> new LoggingFilter();
            default -> throw new IllegalArgumentException("Unsupported filter: " + filterName);
        };
    }
}
