package study;

import study.container.ServletContainer;
import study.handler.DynamicHandler;
import study.server.HttpServer;
import study.servlet.HelloServlet;

public class Main {
    public static void main(String[] args) {
        ServletContainer servletContainer = new ServletContainer();
        servletContainer.register("/hello", new HelloServlet());
        Runtime.getRuntime().addShutdownHook(new Thread(servletContainer::destroyAll));

        DynamicHandler dynamicHandler = new DynamicHandler(servletContainer);

        HttpServer httpServer = new HttpServer(8080, dynamicHandler);
        httpServer.start();
    }
}
