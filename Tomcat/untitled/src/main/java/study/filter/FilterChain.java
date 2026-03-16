package study.filter;

import study.handler.Handler;
import study.http.HttpRequest;
import study.http.HttpResponse;

import java.util.List;

public class FilterChain {
    private final List<Filter> filters;
    private final Handler targetHandler;
    private int index;

    public FilterChain(List<Filter> filters, Handler targetHandler) {
        this.filters = filters;
        this.targetHandler = targetHandler;
        this.index = 0;
    }

    public HttpResponse doFilter(HttpRequest request) {
        if (index < filters.size()) {
            Filter nextFilter = filters.get(index++);
            return nextFilter.doFilter(request, this);
        }

        return targetHandler.handle(request);
    }
}
