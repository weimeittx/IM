package cn.dunn.web.login;

import cn.dunn.mode.HttpResult;
import cn.dunn.mode.HttpResultWrap;
import cn.dunn.mode.User;
import cn.dunn.mongo.UserRepository;
import cn.dunn.util.MD5Util;
import com.alibaba.fastjson.JSONObject;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/9/21.
 */
//@Component
public class LoginController implements InitializingBean {
    @Resource
    private Router router;

    @Resource
    private UserRepository userRepository;

    @Override
    public void afterPropertiesSet() throws Exception {
        login();
    }

    private void login() {
        router.route("/user/login").handler(routingContext -> {
            HttpServerRequest request = routingContext.request();
            HttpServerResponse response = routingContext.response();
            String username = request.getParam("username");
            String password = request.getParam("password");
            response.putHeader("content-type", "text/html;charset=UTF-8");
            if (StringUtils.hasLength(username) && StringUtils.hasLength(password)) {
                User user = userRepository.getByUsernameAndPassword(username, MD5Util.MD5(password));
                if (user != null) {
                    response.end(new HttpResultWrap(JSONObject.toJSONString(new HttpResult(user))).toString());
                    return;
                }
            }
            response.end(new HttpResultWrap(JSONObject.toJSONString(new HttpResult("帐号或者密码错误!"))).toString());
        });
    }
}
