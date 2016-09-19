package cn.dunn.mode;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 消息
 */
@Document
public class Message {
    @Id
    private String id;
    /**
     * 发送人
     */
    private User from;
    /**
     * 目的的
     */
    private User to;
    /**
     * 消息类型
     */
    private String type;
    /**
     * 内容
     */
    private String content;
    /**
     * 消息创建时间
     */
    private Long createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }
}
