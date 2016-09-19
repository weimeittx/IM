package cn.dunn.akka

import akka.actor.ActorSystem

/**
  * Created by Administrator on 2016/9/18.
  */
object AkkaBootstrap {
  def apply(): Unit = {
    val actorSystem = ActorSystem()
  }
}
