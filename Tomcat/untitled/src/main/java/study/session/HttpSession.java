package study.session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HttpSession {
    private final String id;
    private final long creationTime;
    private volatile long lastAccessedTime;
    private final Map<String, Object> attributes = new ConcurrentHashMap<>();

    public HttpSession(String id) {
        this.id = id;
        this.creationTime = System.currentTimeMillis();
        this.lastAccessedTime = this.creationTime;
    }

    public String getId() {
        return id;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void touch() {
        this.lastAccessedTime = System.currentTimeMillis();
    }

    public void setAttribute(String name, Object value) {
        attributes.put(name, value);
    }

    public Object getAttribute(String name) {
        return attributes.get(name);
    }
}
