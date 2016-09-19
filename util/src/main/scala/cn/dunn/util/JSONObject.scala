package cn.dunn.util

import cn.dunn.message.{GroupMessage, ChatMessage}
import org.json4s.ShortTypeHints
import org.json4s.native.Serialization

/**
  * 转换json工具类
  */
object JSONObject {
  implicit val formats = Serialization.formats(
    ShortTypeHints(
      List(
        classOf[ChatMessage],
        classOf[GroupMessage]
      )
    )
  )

  def toJsonString(anyRef: AnyRef): String = {
    Serialization.write(anyRef)
  }
}
