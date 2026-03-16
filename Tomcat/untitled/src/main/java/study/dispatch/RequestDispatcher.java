package study.dispatch;

import study.container.ServletContainer;
import study.exception.BadRequestException;
import study.handler.Handler;
import study.http.HttpRequest;
import study.http.HttpResponse;

public class RequestDispatcher {
    private final Handler staticHandler;
    private final Handler dynamicHandler;
    private final ServletContainer servletContainer;

    public RequestDispatcher(Handler staticHandler, Handler dynamicHandler, ServletContainer servletContainer) {
        this.staticHandler = staticHandler;
        this.dynamicHandler = dynamicHandler;
        this.servletContainer = servletContainer;
    }

    public HttpResponse dispatch(HttpRequest request) {
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
