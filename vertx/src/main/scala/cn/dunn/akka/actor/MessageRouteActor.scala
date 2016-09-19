package cn.dunn.akka.actor

import akka.actor._
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.MemberUp
import cn.dunn.message.{ChatMessage, ChatMessageActorUp, GroupMessage, GroupMessageActorUp}

import scala.collection.mutable.ListBuffer

/**
  * 消息路由actor
  */
class MessageRouteActor extends Actor with ActorLogging {

  val cluster = Cluster(context.system)

  val chatMessageActors = ListBuffer[ActorRef]()
  val groupMessageActors = ListBuffer[ActorRef]()

  val CHAT_MESSAGE_ACTOR = "chatMessageActor"
  val GROUP_MESSAGE_ACTOR = "groupMessageActor"
  var chatMessageIndex = 1
  var groupMessageIndex = 1

  override def preStart(): Unit = {
    cluster.subscribe(self, classOf[MemberUp])
  }


  override def postStop(): Unit = {
    cluster.unsubscribe(self)
  }

  override def receive: Receive = {
    case msg: ChatMessage =>
      if (chatMessageActors.size >= chatMessageIndex) {
        chatMessageIndex = 0
      }
      chatMessageActors(chatMessageActors.size % chatMessageIndex) ! msg
      chatMessageIndex += 1


    case msg: GroupMessage =>
      if (groupMessageActors.size >= groupMessageIndex) {
        groupMessageIndex = 0
      }
      groupMessageActors(groupMessageActors.size % groupMessageIndex) ! msg
      groupMessageIndex += 1


    case ChatMessageActorUp =>
      chatMessageActors += sender
      context.watch(sender())

    case up: MemberUp =>
      val member = up.member
      if (member.hasRole(CHAT_MESSAGE_ACTOR)) {
        context.actorSelection(RootActorPath(member.address) / "user" / CHAT_MESSAGE_ACTOR) ! ChatMessageActorUp()
      } else if (member.hasRole(GROUP_MESSAGE_ACTOR)) {
        context.actorSelection(RootActorPath(member.address) / "user" / GROUP_MESSAGE_ACTOR) ! GroupMessageActorUp()
      }


    case Terminated(ref) =>
      chatMessageActors -= ref
      groupMessageActors -= ref

    case _ =>
  }
}
