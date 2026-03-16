package study;

import study.config.AppConfig;
import study.config.AppConfigLoader;
import study.config.FilterConfigFactory;
import study.config.ServletConfigLoader;
import study.container.ServletContainer;
import study.dispatch.RequestDispatcher;
import study.filter.Filter;
import study.handler.DynamicHandler;
import study.handler.Handler;
import study.resource.StaticResourceHandler;
import study.server.HttpServer;
import study.session.SessionManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfigLoader().load("application.properties");

        ServletContainer servletContainer = new ServletContainer();
        new ServletConfigLoader().registerServlets(servletContainer, appConfig.getServletMappings());
        Runtime.getRuntime().addShutdownHook(new Thread(servletContainer::destroyAll));

        Handler dynamicHandler = new DynamicHandler(servletContainer);
        Handler staticHandler = new StaticResourceHandler();
        SessionManager sessionManager = new SessionManager();
        List<Filter> filters = new FilterConfigFactory(sessionManager).createFilters(appConfig.getFilters());
        RequestDispatcher dispatcher = new RequestDispatcher(staticHandler, dynamicHandler, servletContainer, filters);

        HttpServer httpServer = new HttpServer(appConfig.getPort(), dispatcher);
        httpServer.start();
    }
}
