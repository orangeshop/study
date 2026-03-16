package study.connection;

import study.http.HttpRequest;
import study.http.HttpResponse;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientHandler {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void handler() {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        ) {
            HttpRequest request = parseRequest(reader);

            System.out.println("method = " + request.getMethod());
            System.out.println("path = " + request.getPath());
            System.out.println("version = " + request.getVersion());
            System.out.println("headers = " + request.getHeaders());

            HttpResponse response = new HttpResponse();
            response.setBody("Hello World");

            writer.write(response.toHttpString());
            writer.flush();

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
