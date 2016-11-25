package com.winry.mahjong.actor

import akka.actor.{Actor, ActorLogging}
import akka.io.Tcp.{PeerClosed, Received}
import com.winry.mahjong.packet.packet.PacketMSG
import com.winry.mahjong.packet.packet.PacketMSG.Msg

/**
  * Created by User on 11/24/2016.
  */
class Dispatcher extends Actor with ActorLogging {

  override def receive: Receive = {
    case Received(data) => {
      log.debug("receive:" + data)
      val packetMessage = PacketMSG.parseFrom(data.toArray)
      packetMessage.msg match {
        case Msg.Login(l) =>
        case _ =>
      }
    }
    case PeerClosed => context stop self
  }
}
