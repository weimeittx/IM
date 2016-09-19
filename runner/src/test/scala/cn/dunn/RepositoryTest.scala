package cn.dunn

import cn.dunn.mode.User
import cn.dunn.mongo.UserRepository
import cn.dunn.util.MD5Util
import com.alibaba.fastjson.{JSON, JSONObject}
import org.springframework.context.support.ClassPathXmlApplicationContext

/**
  * Created by Administrator on 2016/9/19.
  */
object RepositoryTest extends App {
  val context = new ClassPathXmlApplicationContext("applicationContext.xml")
  val userRepository = context.getBean(classOf[UserRepository])
    val user: User = userRepository.getByUsernameAndPassword("422450455@qq.com",MD5Util.MD5("1234561"))
    println(user.getId)
}
