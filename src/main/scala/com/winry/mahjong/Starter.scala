package com.winry.mahjong

import akka.actor.{ActorSystem, Props}
import com.winry.mahjong.actor.{Lobby, Server}

/**
  * Created by User on 11/25/2016.
  */
object Starter extends App {

  val actorSystem = ActorSystem("server")
  val server = actorSystem.actorOf(Server.props("127.0.0.1", 8888), "front")
  val lobby = actorSystem.actorOf(Props[Lobby], "lobby")
}
