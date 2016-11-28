package com.winry.mahjong.actor

import akka.actor.{Actor, ActorLogging}
import akka.io.Tcp.{PeerClosed, Received}
import com.winry.mahjong.Session
import com.winry.mahjong.message.PacketMSG
import com.winry.mahjong.message.PacketMSG.Msg
import com.winry.mahjong.service.LoginService

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
        case Msg.LoginReq(l) => LoginService.login(session, l)
        case Msg.ReadyReq(r) =>
        case _ =>
      }
    case PeerClosed => context stop self
  }
}
