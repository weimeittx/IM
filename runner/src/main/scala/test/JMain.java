package test;

import cn.dunn.constant.Source;
import cn.dunn.message.ChatMessage;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * Created by Administrator on 2016/9/19.
 */
public class JMain {
    public static  void main(String[] args){
        String s = JSONObject.toJSONString(ChatMessage.apply("from", "to", "content", new Date(), Source.WEB()));
        System.out.println(s);
    }
}
