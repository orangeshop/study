package study.servlet;

import study.http.HttpRequest;
import study.http.HttpResponse;

public class UserServlet implements Servlet {
    @Override
    public void init() {
        System.out.println("UserServlet init");
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.setStatus(200, "OK");
        response.addHeader("Content-Type", "text/plain; charset=UTF-8");
        response.setBody("User Page");
    }

    @Override
    public void destroy() {
        System.out.println("UserServlet destroy");
    }
}
