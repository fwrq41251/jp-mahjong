package com.winry.mahjong.actor

import akka.actor.Actor
import com.winry.mahjong.Game

/**
  * Created by User on 11/17/2016.
  */
class GameHandler extends Actor {

  val game: Game = ???

  override def receive: Receive = ???
}
