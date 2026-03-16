package study.handler;


import study.http.HttpRequest;
import study.http.HttpResponse;

public class DynamicHandler implements Handler {
    @Override
    public HttpResponse handle(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        if ("/hello".equals(request.getPath())) {
            response.setStatus(200, "OK");
            response.addHeader("Content-Type", "text/plain; charset=UTF-8");
            response.setBody("Hello Dynamic World");
            return response;
        }

        response.setStatus(404, "Not Found");
        response.addHeader("Content-Type", "text/plain; charset=UTF-8");
        response.setBody("404 Not Found");
        return response;
    }
}
