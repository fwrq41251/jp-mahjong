package com.winry.mahjong.actor

import akka.actor.{Actor, ActorLogging}
import akka.io.Tcp.{PeerClosed, Received}

/**
  * Created by User on 11/24/2016.
  */
class Dispatcher extends Actor with ActorLogging {

  override def receive: Receive = {
    case Received(data) => {
      log.debug("receive" + data)
//      sender() ! Tcp.Write(data)
    }
    case PeerClosed => context stop self
  }
}
