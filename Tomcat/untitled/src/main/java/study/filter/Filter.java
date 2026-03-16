package study.filter;

import study.http.HttpRequest;
import study.http.HttpResponse;

public interface Filter {
    HttpResponse doFilter(HttpRequest request, FilterChain chain);
}
