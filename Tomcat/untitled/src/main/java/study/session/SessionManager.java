package study.session;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private final Map<String, HttpSession> sessions = new ConcurrentHashMap<>();

    public HttpSession getSession(String sessionId) {
        if (sessionId == null || sessionId.isBlank()) {
            return null;
        }

        HttpSession session = sessions.get(sessionId);
        if (session != null) {
            session.touch();
        }
        return session;
    }

    public HttpSession createSession() {
        String sessionId = UUID.randomUUID().toString();
        HttpSession session = new HttpSession(sessionId);
        sessions.put(sessionId, session);
        return session;
    }
}
