package study.servlet;

import study.http.HttpRequest;
import study.http.HttpResponse;

public interface Servlet {
    void service(HttpRequest request, HttpResponse response);
}
