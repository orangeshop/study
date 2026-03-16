package study.resource;

import study.handler.Handler;
import study.http.HttpRequest;
import study.http.HttpResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StaticResourceHandler implements Handler {
    private static final Path WEB_ROOT = Path.of("webapp").toAbsolutePath().normalize();

    @Override
    public HttpResponse handle(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        try {
            String requestPath = request.getPath();

            if (requestPath.equals("/")) {
                requestPath = "/index.html";
            }

            Path filePath = WEB_ROOT.resolve(requestPath.substring(1)).normalize();

            if (!filePath.startsWith(WEB_ROOT)) {
                response.sendError(403, "Forbidden");
                return response;
            }

            if (!Files.exists(filePath) || Files.isDirectory(filePath)) {
                response.sendError(404, "Not Found");
                return response;
            }

            byte[] body = Files.readAllBytes(filePath);

            response.setStatus(200);
            response.setContentType(getContentType(filePath));
            response.setBody(body);

            return response;
        } catch (IOException e) {
            response.sendError(500, "Internal Server Error");
            return response;
        }
    }

    private String getContentType(Path filePath) {
        String fileName = filePath.getFileName().toString();

        if (fileName.endsWith(".html")) return "text/html; charset=UTF-8";
        if (fileName.endsWith(".css")) return "text/css; charset=UTF-8";
        if (fileName.endsWith(".js")) return "application/javascript; charset=UTF-8";
        if (fileName.endsWith(".png")) return "image/png";
        if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) return "image/jpeg";

        return "text/plain; charset=UTF-8";
    }
}
