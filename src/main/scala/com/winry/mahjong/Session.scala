package com.winry.mahjong

import akka.actor.ActorRef

/**
  * Created by User on 11/25/2016.
  */
class Session(val id: Long, val client: ActorRef) {

  var userId = 0L

  def send(message: Any): Unit = {
    client ! message
  }

}
