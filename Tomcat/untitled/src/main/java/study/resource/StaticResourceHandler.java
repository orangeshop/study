package study.resource;

import study.exception.BadRequestException;
import study.exception.ForbiddenException;
import study.exception.InternalServerErrorException;
import study.exception.NotFoundException;
import study.handler.Handler;
import study.http.HttpRequest;
import study.http.HttpResponse;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class StaticResourceHandler implements Handler {
    private static final Path WEB_ROOT = Path.of("webapp").toAbsolutePath().normalize();

    @Override
    public HttpResponse handle(HttpRequest request) {
        HttpResponse response = new HttpResponse();

        try {
            Path filePath = resolveResourcePath(request.getPath());

            byte[] body = Files.readAllBytes(filePath);

            response.setStatus(200);
            response.setContentType(getContentType(filePath));
            response.setBody(body);

            return response;
        } catch (IOException e) {
            throw new InternalServerErrorException("Internal Server Error", e);
        }
    }

    private Path resolveResourcePath(String rawRequestPath) {
        String requestPath = normalizeRequestPath(rawRequestPath);
        Path filePath = WEB_ROOT.resolve(requestPath.substring(1)).normalize();

        if (!filePath.startsWith(WEB_ROOT)) {
            throw new ForbiddenException("Forbidden");
        }

        if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
            throw new NotFoundException("Not Found");
        }

        return filePath;
    }

    private String normalizeRequestPath(String rawRequestPath) {
        if (rawRequestPath == null || rawRequestPath.isBlank()) {
            throw new BadRequestException("Bad Request");
        }

        String decodedPath = URLDecoder.decode(rawRequestPath, StandardCharsets.UTF_8);
        if (!decodedPath.startsWith("/")) {
            throw new BadRequestException("Bad Request");
        }

        if (decodedPath.contains("\0")) {
            throw new BadRequestException("Bad Request");
        }

        if (decodedPath.contains("\\")) {
            throw new ForbiddenException("Forbidden");
        }

        if (decodedPath.equals("/")) {
            return "/index.html";
        }

        return decodedPath;
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
