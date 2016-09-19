package cn.dunn.akka.actor

import akka.actor._
import akka.cluster.Cluster
import akka.cluster.ClusterEvent.MemberUp
import cn.dunn.message.{ChatMessageActorUp, MessageForwardActorUp, GroupMessage, GroupMessageActorUp}

import scala.collection.mutable.ListBuffer

/**
  * Created by Administrator on 2016/9/18.
  */
class GroupMessageActor extends Actor with ActorLogging {
  val cluster = Cluster(context.system)
  val MESSAGE_FORWARD_ACTOR = "MessageForwardActor"
  val messageForwardActors = ListBuffer[ActorRef]()
  var messageForwardIndex = 0

  override def preStart(): Unit = {
    cluster.subscribe(self, classOf[MemberUp])
  }

  override def postStop(): Unit = {
    cluster.unsubscribe(self)
  }

  override def receive: Receive = {
    case m: GroupMessageActorUp => sender ! m
    case m: GroupMessage => {
      //查询该群的所有成员
      messageForwardIndex += 1
      if (messageForwardIndex >= messageForwardActors.size) {
        messageForwardIndex = 0
      }
      messageForwardActors(messageForwardActors.size % messageForwardIndex) ! m.copy(members = Vector.empty)
    }
    case MessageForwardActorUp => {
      messageForwardActors += sender
      context.watch(sender)
    }
    case m: MemberUp => {
      val member = m.member
      if (member.hasRole(MESSAGE_FORWARD_ACTOR)) {
        context.actorSelection(RootActorPath(member.address) / "user" / MESSAGE_FORWARD_ACTOR) ! MessageForwardActorUp()

      }
    }
    case Terminated(ref) => messageForwardActors -= ref
    case _ =>
  }
}
