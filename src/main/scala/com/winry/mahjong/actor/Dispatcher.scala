package com.winry.mahjong.actor

import akka.actor.{Actor, ActorLogging}
import akka.io.Tcp.{PeerClosed, Received}
import com.winry.mahjong.Session
import com.winry.mahjong.handler.LoginHandler
import com.winry.mahjong.message.PacketMSG
import com.winry.mahjong.message.PacketMSG.Msg

/**
  * every client connection has a seprate
  */
class Dispatcher(ss: Session) extends Actor with ActorLogging {

  val session :Session = ss

  override def receive: Receive = {
    case Received(data) =>
      log.debug("receive:" + data)
      val packetMessage = PacketMSG.parseFrom(data.toArray)
      packetMessage.msg match {
        case Msg.LoginReq(l) => LoginHandler.login(session, l)
        case Msg.ReadyReq(_) =>
        case _ =>
      }
    case PeerClosed => context stop self
  }
}
