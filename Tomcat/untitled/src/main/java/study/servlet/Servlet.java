package study.servlet;

import study.http.HttpRequest;
import study.http.HttpResponse;

public interface Servlet {
    void init();
    void service(HttpRequest request, HttpResponse response);
    void destroy();
}
