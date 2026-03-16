package study.connection;

import study.http.HttpRequest;
import study.http.HttpResponse;
import study.resource.StaticResourceHandler;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler {
    private final Socket socket;
    private final StaticResourceHandler staticResourceHandler = new StaticResourceHandler();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void handle() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                OutputStream outputStream = socket.getOutputStream()
        ) {
            HttpRequest request = parseRequest(reader);
            if (request.getPath() == null) {
                HttpResponse response = new HttpResponse();
                response.setStatus(400, "Bad Request");
                response.addHeader("Content-Type", "text/plain; charset=UTF-8");
                response.setBody("400 Bad Request");

                outputStream.write(response.toHttpBytes());
                outputStream.flush();
                return;
            }
            HttpResponse response = staticResourceHandler.handle(request);

            outputStream.write(response.toHttpBytes());
            outputStream.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                // ignore
            }
        }
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
            String headerName = headerParts[0].trim();
            String headerValue = headerParts[1].trim();
            request.addHeader(headerName, headerValue);
        }

        return request;
    }
}
