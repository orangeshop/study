package study.servlet;

import study.http.HttpRequest;
import study.http.HttpResponse;
import study.session.HttpSession;

public class UserServlet implements Servlet {
    @Override
    public void init() {
        System.out.println("UserServlet init");
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        response.setStatus(200);
        response.setTextPlainContentType();

        String name = request.getParameter("name");
        if (name == null || name.isBlank()) {
            HttpSession session = request.getSession(false);
            if (session == null) {
                response.setBody("User Page");
                return;
            }

            Object username = session.getAttribute("username");
            if (username == null) {
                response.setBody("User Page");
                return;
            }

            response.setBody("User Page: " + username);
            return;
        }

        response.setBody("User Page: " + name);
    }

    @Override
    public void destroy() {
        System.out.println("UserServlet destroy");
    }
}
