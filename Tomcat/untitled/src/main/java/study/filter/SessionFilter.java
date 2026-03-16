package study.filter;

import study.http.HttpRequest;
import study.http.HttpResponse;
import study.session.HttpSession;
import study.session.SessionManager;

public class SessionFilter implements Filter {
    private static final String SESSION_COOKIE_NAME = "JSESSIONID";

    private final SessionManager sessionManager;

    public SessionFilter(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public HttpResponse doFilter(HttpRequest request, FilterChain chain) {
        request.setSessionManager(sessionManager);

        HttpSession session = sessionManager.getSession(request.getCookie(SESSION_COOKIE_NAME));
        if (session != null) {
            request.setSession(session);
        }

        HttpResponse response = chain.doFilter(request);

        HttpSession currentSession = request.getSession(false);
        if (currentSession != null && request.isNewSession()) {
            response.addCookie(SESSION_COOKIE_NAME, currentSession.getId(), true);
        }

        return response;
    }
}
