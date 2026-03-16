package study.connection;

import study.handler.Handler;
import study.http.HttpRequest;
import study.http.HttpResponse;
import study.resource.StaticResourceHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler {
    private final Socket socket;
    private final Handler staticResourceHandler = new StaticResourceHandler();
    private final Handler dynamicHandler;

    public ClientHandler(Socket socket, Handler dynamicHandler) {
        this.socket = socket;
        this.dynamicHandler = dynamicHandler;
    }

    public void handle() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            OutputStream outputStream = socket.getOutputStream();

            try {
                HttpRequest request = parseRequest(reader);
                if (request.getPath() == null) {
                    writeResponse(outputStream, createErrorResponse(400, "Bad Request"));
                    return;
                }

                writeResponse(outputStream, route(request));
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

    private HttpResponse route(HttpRequest request) {
        if ("/hello".equals(request.getPath())) {
            return dynamicHandler.handle(request);
        }
        return staticResourceHandler.handle(request);
    }

    private void writeResponse(OutputStream outputStream, HttpResponse response) throws IOException {
        outputStream.write(response.toHttpBytes());
        outputStream.flush();
    }

    private HttpResponse createErrorResponse(int statusCode, String statusMessage) {
        HttpResponse response = new HttpResponse();
        response.setStatus(statusCode, statusMessage);
        response.addHeader("Content-Type", "text/plain; charset=UTF-8");
        response.setBody(statusCode + " " + statusMessage);
        return response;
    }

    private HttpRequest parseRequest(BufferedReader reader) throws IOException {
        HttpRequest request = new HttpRequest();

        String requestLine = reader.readLine();
        if (requestLine == null || requestLine.isEmpty()) {
            return request;
        }

        String[] requestLineParts = requestLine.split(" ");
        if (requestLineParts.length < 3) {
            return request;
        }

        request.setMethod(requestLineParts[0]);
        request.setPath(requestLineParts[1]);
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

        return request;
    }
}
