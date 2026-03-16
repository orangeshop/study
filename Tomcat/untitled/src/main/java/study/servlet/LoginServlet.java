package study.servlet;

import study.http.HttpRequest;
import study.http.HttpResponse;

public class LoginServlet implements Servlet {
    @Override
    public void init() {
        System.out.println("LoginServlet init");
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.setStatus(200, "OK");
        response.addHeader("Content-Type", "text/plain; charset=UTF-8");
        response.setBody("Login Page");
    }

    @Override
    public void destroy() {
        System.out.println("LoginServlet destroy");
    }
}
