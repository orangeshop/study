package study.server;

import study.connection.ClientHandler;
import study.dispatch.RequestDispatcher;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private final int port;
    private final RequestDispatcher dispatcher;
    private final ExecutorService executorService;

    public HttpServer(int port, RequestDispatcher dispatcher) {
        this.port = port;
        this.dispatcher = dispatcher;
        this.executorService = Executors.newCachedThreadPool();
    }

    public void start() {
        try (ServerSocket s = new ServerSocket(this.port)) {
            System.out.println("서버 시작 " + this.port);

            while (true) {
                Socket socket = s.accept();
                System.out.println("클라이언트 연결 " + socket.getInetAddress());

                ClientHandler clientHandler = new ClientHandler(socket, dispatcher);
                executorService.submit(clientHandler::handle);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }
}
