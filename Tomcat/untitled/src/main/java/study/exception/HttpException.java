package study.exception;

public class HttpException extends RuntimeException {
    private final int statusCode;
    private final String statusMessage;

    public HttpException(int statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public HttpException(int statusCode, String statusMessage, Throwable cause) {
        super(statusMessage, cause);
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
