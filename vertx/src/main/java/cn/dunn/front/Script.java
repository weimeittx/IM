package cn.dunn.front;

import com.alibaba.fastjson.JSONObject;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;

/**
 * Created by Administrator on 2016/9/18.
 */
public interface Script {
    void executeFunctionToOne(String address, String functionName, Object... args);

    void executeFunctionToAll(String address, String functionName, Object... args);

    void executeFunctionToOne(String address, String functionName, DeliveryOptions options, Object... args);

    void executeFunctionToAll(String address, String functionName, DeliveryOptions options, Object... args);

    <T> void executeFunctionToOne(String address, String functionName, Handler<AsyncResult<Message<T>>> replyHandler, Object... args);

    <T> void executeFunctionToOne(String address, String functionName, DeliveryOptions options, Handler<AsyncResult<Message<T>>> replyHandler, Object... args);

    default String decideRealCallFunction(String functionName, Object... args) {
        StringBuilder sb = new StringBuilder();
        String jsonArgs = JSONObject.toJSONString(args);
        if (jsonArgs.startsWith("[") && jsonArgs.endsWith("]")) sb.append(jsonArgs.substring(1, jsonArgs.length() - 1));
        else sb.append(jsonArgs);
        sb.insert(0, "(").insert(0, functionName);
        sb.append(");");
        return sb.toString();
    }
}
