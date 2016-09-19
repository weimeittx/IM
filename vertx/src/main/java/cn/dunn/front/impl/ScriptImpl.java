package cn.dunn.front.impl;

import cn.dunn.front.Script;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;

/**
 * Created by Administrator on 2016/9/18.
 */
public class ScriptImpl implements Script {
    private EventBus eventBus;

    public ScriptImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void executeFunctionToOne(String address, String functionName, Object... args) {
        eventBus.send(address, decideRealCallFunction(functionName, args));
    }

    @Override
    public void executeFunctionToAll(String address, String functionName, Object... args) {
        eventBus.publish(address, decideRealCallFunction(functionName, args));
    }

    @Override
    public void executeFunctionToOne(String address, String functionName, DeliveryOptions options, Object... args) {
        eventBus.send(address, decideRealCallFunction(functionName, args), options);
    }

    @Override
    public void executeFunctionToAll(String address, String functionName, DeliveryOptions options, Object... args) {
        eventBus.publish(address, decideRealCallFunction(functionName, args), options);
    }

    @Override
    public <T> void executeFunctionToOne(String address, String functionName, Handler<AsyncResult<Message<T>>> replyHandler, Object... args) {
        eventBus.send(address, decideRealCallFunction(functionName, args), replyHandler);
    }

    @Override
    public <T> void executeFunctionToOne(String address, String functionName, DeliveryOptions options, Handler<AsyncResult<Message<T>>> replyHandler, Object... args) {
        eventBus.send(address, decideRealCallFunction(functionName, args), options, replyHandler);
    }
}
