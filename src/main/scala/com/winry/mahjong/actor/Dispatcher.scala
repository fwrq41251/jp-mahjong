package com.winry.mahjong.actor

import akka.actor.{Actor, ActorLogging}
import akka.io.Tcp.{PeerClosed, Received, Write}

/**
  * Created by User on 11/24/2016.
  */
class Dispatcher extends Actor with ActorLogging {

  override def receive: Receive = {
    case Received(data) => {
      log.debug("receive" + data)
      sender() ! Write(data)
    }
    case PeerClosed => context stop self
  }
}
