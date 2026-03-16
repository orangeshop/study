package study.handler;

import study.container.ServletContainer;
import study.exception.NotFoundException;
import study.http.HttpRequest;
import study.http.HttpResponse;
import study.servlet.Servlet;

public class DynamicHandler implements Handler {
    private final ServletContainer servletContainer;

    public DynamicHandler(ServletContainer servletContainer) {
        this.servletContainer = servletContainer;
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        Servlet servlet = servletContainer.getServlet(request.getPath());

        if (servlet == null) {
            throw new NotFoundException("Not Found");
        }

        servlet.service(request, response);
        return response;
    }
}
