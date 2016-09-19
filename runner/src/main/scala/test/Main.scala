package test

import java.util.Date

import cn.dunn.constant.Source
import cn.dunn.message.{GroupMessage, ChatMessage}
import org.json4s.ShortTypeHints
import org.json4s.native.Serialization

/**
  * Created by Administrator on 2016/9/19.
  */
object Main extends App {
  implicit val formats = Serialization.formats(
    ShortTypeHints(
      List(
        classOf[ChatMessage],
        classOf[GroupMessage]
      )
    )
  )
  val s: String = Serialization.write(ChatMessage("from", "to", "content", new Date(), Source.WEB))
  println(s)
}
