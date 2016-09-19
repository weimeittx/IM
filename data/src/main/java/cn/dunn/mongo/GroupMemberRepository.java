package cn.dunn.mongo;

import cn.dunn.mode.GroupMember;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GroupMemberRepository extends MongoRepository<GroupMember, String> {

}
