package cn.dunn.mode;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 聊天组
 */
@Document
public class ChatGroup {
    @Id
    private String id;
    /**
     * 群聊名称
     */
    private String chatGroupName;
    /**
     * 公告
     */
    private String affiche;
    /**
     * 头像
     */
    private String head;
    /**
     * 用户在该群组中未读的消息条数
     */
    private Long unReadMessageCount;

    /**
     * 创建时间
     */
    private Long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getChatGroupName() {
        return chatGroupName;
    }

    public void setChatGroupName(String chatGroupName) {
        this.chatGroupName = chatGroupName;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public Long getUnReadMessageCount() {
        return unReadMessageCount;
    }

    public void setUnReadMessageCount(Long unReadMessageCount) {
        this.unReadMessageCount = unReadMessageCount;
    }
}
