package com.winry.mahjong.actor

import akka.actor.{Actor, ActorLogging}
import akka.io.Tcp.{PeerClosed, Received}
import com.winry.mahjong.message.PacketMSG
import com.winry.mahjong.message.PacketMSG.Msg
import com.winry.mahjong.service.LoginService

/**
  * Created by User on 11/24/2016.
  */
class Dispatcher extends Actor with ActorLogging {

  override def receive: Receive = {
    case Received(data) => {
      log.debug("receive:" + data)
      val packetMessage = PacketMSG.parseFrom(data.toArray)
      packetMessage.msg match {
        case Msg.LoginReq(l) => LoginService.login(sender(), l)
        case _ =>
      }
    }
    case PeerClosed => context stop self
  }
}
