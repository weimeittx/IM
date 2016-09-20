package cn.dunn.service;

import java.io.InputStream;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/20.
 */
public interface FileNote<ID> {
    String CREATE_TIME = "createTime";
    String UPDATE_TIME = "updateTime";
    String TYPE = "type";
    String POSTFIX = "postfix";

    ID getId();

    default String MD5() {
        return null;
    }

    default long length() {
        return 0;
    }

    default String postfix() {
        return null;
    }

    default String type() {
        return null;
    }

    default Date createTime() {
        return null;
    }

    default Date updateTime() {
        return null;
    }

    default InputStream inputStream() {
        return null;
    }

    default Map<String, String> metaData() {
        return null;
    }

    default String filename() {
        return null;
    }
}
