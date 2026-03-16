package study.filter;

import study.http.HttpRequest;
import study.http.HttpResponse;

public class LoggingFilter implements Filter {
    @Override
    public HttpResponse doFilter(HttpRequest request, FilterChain chain) {
        long startTime = System.currentTimeMillis();
        HttpResponse response = chain.doFilter(request);
        long duration = System.currentTimeMillis() - startTime;

        System.out.println("[Filter] " + request.getMethod() + " " + request.getPath() + " " + duration + "ms");
        return response;
    }
}
