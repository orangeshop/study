package study.servlet;

import study.http.HttpRequest;
import study.http.HttpResponse;

public class HelloServlet implements Servlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.setStatus(200, "OK");
        response.addHeader("Content-Type", "text/plain; charset=UTF-8");
        response.setBody("Hello Servlet World");
    }
}
