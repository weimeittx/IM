package cn.dunn.message

import java.util.Date


/**
  * Created by Administrator on 2016/9/19.
  */
case class ChatMessage(from: String, to: String, content: String, sendDate: Date, source: String)

case class GroupMessage(from: String, to: String, content: String, sendDate: Date, source: String, members: Vector[String])

case class ChatMessageActorUp()

case class GroupMessageActorUp()

case class MessageForwardActorUp()
