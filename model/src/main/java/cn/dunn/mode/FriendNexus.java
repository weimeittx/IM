package cn.dunn.mode;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 好友关系
 */
@Document
public class FriendNexus {
    @Id
    private String id;
    /**
     * 自己
     */
    @DBRef
    private User self;

    /**
     * 好友
     */
    @DBRef
    private User friend;
    /**
     * 最后拉取对方的时间
     */
    private Long lastReadTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getSelf() {
        return self;
    }

    public void setSelf(User self) {
        this.self = self;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }

    public Long getLastReadTime() {
        return lastReadTime;
    }

    public void setLastReadTime(Long lastReadTime) {
        this.lastReadTime = lastReadTime;
    }
}
