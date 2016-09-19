package cn.dunn.akka.actor

import akka.actor.{Actor, ActorLogging}
import cn.dunn.message.{MessageForwardActorUp, GroupMessage, ChatMessage}
import cn.dunn.util.JSONObject
import io.vertx.core.Vertx

/**
  * Created by Administrator on 2016/9/19.
  */
class MessageForwardActor(vertx: Vertx) extends Actor with ActorLogging {
  val eventBus = vertx.eventBus()

  override def receive: Receive = {
    case m: MessageForwardActorUp => sender() ! m
    case m: ChatMessage =>
      eventBus.send(m.to, JSONObject.toJsonString(m))
    case m: GroupMessage =>
      m.members.par.foreach(eventBus.send(_, JSONObject.toJsonString(m)))

    case _ =>
  }

}
