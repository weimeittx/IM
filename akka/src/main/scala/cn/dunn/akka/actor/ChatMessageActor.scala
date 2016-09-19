package cn.dunn.akka.actor

import akka.actor.Actor
import cn.dunn.message.{ChatMessage, ChatMessageActorUp}

/**
  * Created by Administrator on 2016/9/18.
  */
class ChatMessageActor extends Actor {
  override def receive: Receive = {
    case m: ChatMessageActorUp => sender ! m
    case m: ChatMessage =>
    case _ =>
  }
}
