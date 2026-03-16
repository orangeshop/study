package study;

import study.container.ServletContainer;
import study.dispatch.RequestDispatcher;
import study.filter.Filter;
import study.filter.LoggingFilter;
import study.handler.DynamicHandler;
import study.handler.Handler;
import study.resource.StaticResourceHandler;
import study.server.HttpServer;
import study.servlet.HelloServlet;
import study.servlet.LoginServlet;
import study.servlet.UserServlet;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ServletContainer servletContainer = new ServletContainer();
        servletContainer.register("/hello", new HelloServlet());
        servletContainer.register("/user", new UserServlet());
        servletContainer.register("/login", new LoginServlet());
        Runtime.getRuntime().addShutdownHook(new Thread(servletContainer::destroyAll));

        Handler dynamicHandler = new DynamicHandler(servletContainer);
        Handler staticHandler = new StaticResourceHandler();
        List<Filter> filters = List.of(new LoggingFilter());
        RequestDispatcher dispatcher = new RequestDispatcher(staticHandler, dynamicHandler, servletContainer, filters);

        HttpServer httpServer = new HttpServer(8080, dispatcher);
        httpServer.start();
    }
}
