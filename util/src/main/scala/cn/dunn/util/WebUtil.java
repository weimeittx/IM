package cn.dunn.util;

import cn.dunn.constant.Constant;
import cn.dunn.mode.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/9/22.
 */
public class WebUtil {
    public static User LoginUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(Constant.USER_SESSION);

    }
}
