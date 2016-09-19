package cn.dunn.mongo;

import cn.dunn.mode.GroupMember;
import cn.dunn.mode.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GroupMemberRepository extends MongoRepository<GroupMember, String> {
    List<GroupMember> findByMember(User member);
}
