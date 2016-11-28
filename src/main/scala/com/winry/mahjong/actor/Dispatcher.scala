package com.winry.mahjong.actor

import akka.actor.{Actor, ActorLogging, ActorSelection}
import akka.io.Tcp.{PeerClosed, Received}
import com.winry.mahjong.Session
import com.winry.mahjong.handler.GameHandler.Ready
import com.winry.mahjong.handler.LoginHandler.Login
import com.winry.mahjong.message.PacketMSG
import com.winry.mahjong.message.PacketMSG.Msg

/**
  * Every connection has a separated dispatcher that holds a unique session.
  */
class Dispatcher(ss: Session) extends Actor with ActorLogging {

  val session: Session = ss

  val loginHandler: ActorSelection = context.actorSelection("akka://server/user/login")
  val gameHandler: ActorSelection = context.actorSelection("akka://server/user/game")


  override def receive: Receive = {
    case Received(data) =>
      log.debug("receive:" + data)
      val packetMessage = PacketMSG.parseFrom(data.toArray)
      packetMessage.msg match {
        case Msg.LoginReq(l) => loginHandler ! Login(session, l)
        case Msg.ReadyReq(_) => gameHandler ! Ready(session)
        case _ =>
      }
    case PeerClosed => context stop self
  }
}
