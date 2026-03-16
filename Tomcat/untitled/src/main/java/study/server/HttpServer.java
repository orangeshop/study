package study.server;

import study.connection.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private final int port;

    public HttpServer(int port) {
        this.port = port;
    }

    public void start() {

        try (ServerSocket s = new ServerSocket(this.port)) {
            System.out.println("서버 시작 " + this.port);

            while (true){
                Socket socket = s.accept();
                System.out.println("클라이언트 연결 " + socket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.handler();

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {

        }


    }
}
