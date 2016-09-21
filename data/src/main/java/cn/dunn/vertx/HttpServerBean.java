package cn.dunn.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//@Component
public class HttpServerBean implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private Router router;
    @Resource
    private Vertx vertx;

    @Value("${web.httpPort}")
    private String httpPort;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(router::accept).listen(Integer.parseInt(httpPort));
    }
}
