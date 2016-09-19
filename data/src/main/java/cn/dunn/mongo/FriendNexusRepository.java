package cn.dunn.mongo;

import cn.dunn.mode.FriendNexus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FriendNexusRepository extends MongoRepository<FriendNexus, String> {
    /**
     * 查询自己的好友
     * @param id
     * @return
     */
    List<FriendNexus> findBySelf_id(String id);
}
