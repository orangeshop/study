package study.server;

import study.connection.ClientHandler;
import study.handler.Handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private final int port;
    private final Handler dynamicHandler;

    public HttpServer(int port, Handler dynamicHandler) {
        this.port = port;
        this.dynamicHandler = dynamicHandler;
    }

    public void start() {
        try (ServerSocket s = new ServerSocket(this.port)) {
            System.out.println("서버 시작 " + this.port);

            while (true) {
                Socket socket = s.accept();
                System.out.println("클라이언트 연결 " + socket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(socket, dynamicHandler);
                clientHandler.handle();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
