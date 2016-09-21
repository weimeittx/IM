package cn.dunn.vertx;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

//@Component
public class RouterBean implements FactoryBean<Router> {
    @Resource
    private Vertx vertx;

    @Override
    public Router getObject() throws Exception {
        return Router.router(vertx);
    }

    @Override
    public Class<?> getObjectType() {
        return Router.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
