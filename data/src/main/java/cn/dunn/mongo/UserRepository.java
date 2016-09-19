package cn.dunn.mongo;

import cn.dunn.mode.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User getByUsernameAndPassword(String username, String password);
}
