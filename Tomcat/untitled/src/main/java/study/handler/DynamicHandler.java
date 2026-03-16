package study.handler;


import study.http.HttpRequest;
import study.http.HttpResponse;
import study.servlet.HelloServlet;
import study.servlet.Servlet;

import java.util.HashMap;
import java.util.Map;

public class DynamicHandler implements Handler {
    private final Map<String, Servlet> servletMap = new HashMap<>();

    public DynamicHandler() {
        servletMap.put("/hello", new HelloServlet());
    }

    @Override
    public HttpResponse handle(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        Servlet servlet = servletMap.get(request.getPath());
        if (servlet == null) {
            response.setStatus(404, "Not Found");
            response.addHeader("Content-Type", "text/plain; charset=UTF-8");
            response.setBody("404 Not Found");
            return response;
        }

        servlet.service(request, response);
        return response;
    }
}
