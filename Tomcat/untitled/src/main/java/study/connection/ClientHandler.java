package study.connection;

import study.dispatch.RequestDispatcher;
import study.exception.BadRequestException;
import study.exception.HttpException;
import study.http.HttpRequest;
import study.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class ClientHandler {
    private final Socket socket;
    private final RequestDispatcher dispatcher;

    public ClientHandler(Socket socket, RequestDispatcher dispatcher) {
        this.socket = socket;
        this.dispatcher = dispatcher;
    }

    public void handle() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            OutputStream outputStream = socket.getOutputStream();

            try {
                HttpRequest request = parseRequest(reader);
                writeResponse(outputStream, dispatcher.dispatch(request));
            } catch (HttpException e) {
                writeResponse(outputStream, createErrorResponse(e.getStatusCode(), e.getStatusMessage()));
            } catch (Exception e) {
                writeResponse(outputStream, createErrorResponse(500, "Internal Server Error"));
            }
        } catch (IOException e) {
            // ignore because the connection may already be broken
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // ignore
            }
        }
    }

    private void writeResponse(OutputStream outputStream, HttpResponse response) throws IOException {
        outputStream.write(response.toHttpBytes());
        outputStream.flush();
    }

    private HttpResponse createErrorResponse(int statusCode, String statusMessage) {
        HttpResponse response = new HttpResponse();
        response.sendError(statusCode, statusMessage);
        return response;
    }

    private HttpRequest parseRequest(BufferedReader reader) throws IOException {
        HttpRequest request = new HttpRequest();

        String requestLine = reader.readLine();
        if (requestLine == null || requestLine.isEmpty()) {
            throw new BadRequestException("Bad Request");
        }

        String[] requestLineParts = requestLine.split(" ");
        if (requestLineParts.length < 3) {
            throw new BadRequestException("Bad Request");
        }

        request.setMethod(requestLineParts[0]);
        parsePath(request, requestLineParts[1]);
        request.setVersion(requestLineParts[2]);

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }

            String[] headerParts = line.split(":", 2);
            if (headerParts.length < 2) {
                continue;
            }

            String headerName = headerParts[0].trim();
            String headerValue = headerParts[1].trim();
            request.addHeader(headerName, headerValue);
        }

        parseBody(reader, request);
        parseCookies(request);

        return request;
    }

    private void parsePath(HttpRequest request, String rawPath) {
        int queryStartIndex = rawPath.indexOf('?');
        if (queryStartIndex < 0) {
            request.setPath(rawPath);
            return;
        }

        request.setPath(rawPath.substring(0, queryStartIndex));
        String queryString = rawPath.substring(queryStartIndex + 1);
        request.setQueryString(queryString);
        parseParameters(queryString, request);
    }

    private void parseBody(BufferedReader reader, HttpRequest request) throws IOException {
        String contentLengthHeader = request.getHeader("Content-Length");
        if (contentLengthHeader == null) {
            return;
        }

        int contentLength;
        try {
            contentLength = Integer.parseInt(contentLengthHeader);
        } catch (NumberFormatException e) {
            throw new BadRequestException("Invalid Content-Length");
        }

        if (contentLength <= 0) {
            return;
        }

        char[] bodyChars = new char[contentLength];
        int totalRead = 0;
        while (totalRead < contentLength) {
            int read = reader.read(bodyChars, totalRead, contentLength - totalRead);
            if (read == -1) {
                break;
            }
            totalRead += read;
        }

        String body = new String(bodyChars, 0, totalRead);
        request.setBody(body);

        String contentType = request.getHeader("Content-Type");
        if (contentType != null && contentType.startsWith("application/x-www-form-urlencoded")) {
            parseParameters(body, request);
        }
    }

    private void parseParameters(String source, HttpRequest request) {
        if (source == null || source.isEmpty()) {
            return;
        }

        String[] pairs = source.split("&");
        for (String pair : pairs) {
            if (pair.isEmpty()) {
                continue;
            }

            String[] parts = pair.split("=", 2);
            String name = decode(parts[0]);
            String value = parts.length > 1 ? decode(parts[1]) : "";
            request.addParameter(name, value);
        }
    }

    private String decode(String value) {
        return URLDecoder.decode(value, StandardCharsets.UTF_8);
    }

    private void parseCookies(HttpRequest request) {
        String cookieHeader = request.getHeader("Cookie");
        if (cookieHeader == null || cookieHeader.isBlank()) {
            return;
        }

        String[] cookies = cookieHeader.split(";");
        for (String cookie : cookies) {
            String[] parts = cookie.trim().split("=", 2);
            if (parts.length < 2) {
                continue;
            }

            request.addCookie(parts[0].trim(), parts[1].trim());
        }
    }
}
