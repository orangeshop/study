package study.handler;

import study.http.HttpRequest;
import study.http.HttpResponse;

public interface Handler {
    HttpResponse handle(HttpRequest request);
}
