package study;


import study.server.HttpServer;

public class Main {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer(8080);
        httpServer.start();
    }
}