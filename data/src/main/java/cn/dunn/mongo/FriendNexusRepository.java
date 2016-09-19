package cn.dunn.mongo;

import cn.dunn.mode.FriendNexus;
import cn.dunn.mode.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FriendNexusRepository extends MongoRepository<FriendNexus, String> {

    /**
     * 查询自己的好友
     * @param self
     * @return
     */
    List<FriendNexus> findBySelf(User self);
}
