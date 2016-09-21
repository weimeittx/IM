package cn.dunn.mode;

/**
 * Created by Administrator on 2016/9/21.
 */
public class HttpResultWrap {
    private String json;

    public HttpResultWrap(String json) {
        this.json = json;
    }

    @Override
    public String toString() {
        return "var callback = " + json;
    }
}
