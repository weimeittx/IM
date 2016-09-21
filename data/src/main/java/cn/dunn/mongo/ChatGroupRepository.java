package cn.dunn.mongo;

import cn.dunn.mode.ChatGroup;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface  ChatGroupRepository extends MongoRepository<ChatGroup, String> {
}
