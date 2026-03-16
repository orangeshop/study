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
        response.setStatus(200);
        response.setTextPlainContentType();

        String username = request.getParameter("username");
        if (username == null || username.isBlank()) {
            response.setBody("Login Page");
            return;
        }

        response.setBody("Login Page: " + username);
    }

    @Override
    public void destroy() {
        System.out.println("LoginServlet destroy");
    }
}
