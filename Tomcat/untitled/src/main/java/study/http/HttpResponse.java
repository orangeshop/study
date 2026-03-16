package study.http;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponse {
    private static final Map<Integer, String> DEFAULT_STATUS_MESSAGES = Map.of(
            200, "OK",
            302, "Found",
            400, "Bad Request",
            403, "Forbidden",
            404, "Not Found",
            500, "Internal Server Error"
    );

    private int statusCode;
    private String statusMessage;
    private final Map<String, String> headers = new LinkedHashMap<>();
    private byte[] body = new byte[0];

    public HttpResponse() {
        this.statusCode = 200;
        this.statusMessage = "OK";
    }

    public void setStatus(int statusCode) {
        setStatus(statusCode, DEFAULT_STATUS_MESSAGES.getOrDefault(statusCode, ""));
    }

    public void setStatus(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }

    public void setBody(String body) {
        this.body = body.getBytes(StandardCharsets.UTF_8);
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public void setContentType(String contentType) {
        addHeader("Content-Type", contentType);
    }

    public void addCookie(String name, String value, boolean httpOnly) {
        StringBuilder cookie = new StringBuilder();
        cookie.append(name).append("=").append(value).append("; Path=/");

        if (httpOnly) {
            cookie.append("; HttpOnly");
        }

        addHeader("Set-Cookie", cookie.toString());
    }

    public void setTextPlainContentType() {
        setContentType("text/plain; charset=UTF-8");
    }

    public void setHtmlContentType() {
        setContentType("text/html; charset=UTF-8");
    }

    public void setJsonContentType() {
        setContentType("application/json; charset=UTF-8");
    }

    public void sendError(int statusCode, String message) {
        setStatus(statusCode, message);
        setTextPlainContentType();
        setBody(statusCode + " " + message);
    }

    public void redirect(String location) {
        setStatus(302);
        addHeader("Location", location);
        setBody(new byte[0]);
    }

    public byte[] toHttpBytes() {
        headers.put("Content-Length", String.valueOf(body.length));

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 ")
          .append(statusCode)
          .append(" ")
          .append(statusMessage)
          .append("\r\n");

        for (Map.Entry<String, String> entry : headers.entrySet()) {
            sb.append(entry.getKey())
              .append(": ")
              .append(entry.getValue())
              .append("\r\n");
        }

        sb.append("\r\n");

        byte[] headerBytes = sb.toString().getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[headerBytes.length + body.length];

        System.arraycopy(headerBytes, 0, result, 0, headerBytes.length);
        System.arraycopy(body, 0, result, headerBytes.length, body.length);

        return result;
    }
}
