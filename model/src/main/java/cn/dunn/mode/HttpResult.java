package cn.dunn.mode;

/**
 * http ajax请求返回参数
 *
 * @param <T>
 */
public class HttpResult {
    private Boolean success = true;
    private String msg;
    private Object result;

    public HttpResult() {
    }

    public HttpResult(Object result) {
        this.result = result;
    }

    public HttpResult(String msg) {
        this.msg = msg;
        this.success = false;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
