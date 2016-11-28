package com.winry.mahjong

import akka.actor.{ActorSystem, Props}
import com.winry.mahjong.actor.Server
import com.winry.mahjong.handler.{GameHandler, LoginHandler}

/**
  * Created by User on 11/25/2016.
  */
object Starter extends App {

  val actorSystem = ActorSystem("server")
  val server = actorSystem.actorOf(Props(new Server("127.0.0.1", 8888)), "server")
  val game = actorSystem.actorOf(Props[GameHandler], "game")
  val login = actorSystem.actorOf(Props[LoginHandler], "login")
}
