package study.dispatch;

import study.container.ServletContainer;
import study.exception.BadRequestException;
import study.filter.Filter;
import study.filter.FilterChain;
import study.handler.Handler;
import study.http.HttpRequest;
import study.http.HttpResponse;

import java.util.List;

public class RequestDispatcher {
    private final Handler staticHandler;
    private final Handler dynamicHandler;
    private final ServletContainer servletContainer;
    private final List<Filter> filters;

    public RequestDispatcher(Handler staticHandler, Handler dynamicHandler, ServletContainer servletContainer) {
        this(staticHandler, dynamicHandler, servletContainer, List.of());
    }

    public RequestDispatcher(Handler staticHandler, Handler dynamicHandler, ServletContainer servletContainer, List<Filter> filters) {
        this.staticHandler = staticHandler;
        this.dynamicHandler = dynamicHandler;
        this.servletContainer = servletContainer;
        this.filters = filters;
    }

    public HttpResponse dispatch(HttpRequest request) {
        FilterChain filterChain = new FilterChain(filters, this::dispatchInternal);
        return filterChain.doFilter(request);
    }

    private HttpResponse dispatchInternal(HttpRequest request) {
        String path = request.getPath();

        if (path == null) {
            throw new BadRequestException("Bad Request");
        }

        if (isDynamicRequest(path)) {
            return dynamicHandler.handle(request);
        }

        return staticHandler.handle(request);
    }

    private boolean isDynamicRequest(String path) {
        return servletContainer.hasServlet(path);
    }
}
