package cn.dunn.message

import java.util.Date

import cn.dunn.constant.Source

/**
  * Created by Administrator on 2016/9/19.
  */
case class ChatMessage(from: String, to: String, content: String, sendDate: Date, source: Source)

case class GroupMessage(from: String, to: String, content: String, sendDate: Date, source: Source, members: Vector[String])

case class ChatMessageActorUp()

case class GroupMessageActorUp()

case class MessageForwardActorUp()
