package cn.dunn.mode;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 群组成员
 */
@Document
public class GroupMember {
    @Id
    private String id;
    /**
     * 所属聊天组
     */
    @DBRef
    private ChatGroup chatGroup;

    /**
     * 成员
     */
    @DBRef
    private User member;
    /**
     * 该成员在群组中最后读取的消息时间
     */
    private Long lastReadTime;

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChatGroup getChatGroup() {
        return chatGroup;
    }

    public void setChatGroup(ChatGroup chatGroup) {
        this.chatGroup = chatGroup;
    }

    public Long getLastReadTime() {
        return lastReadTime;
    }

    public void setLastReadTime(Long lastReadTime) {
        this.lastReadTime = lastReadTime;
    }
}
