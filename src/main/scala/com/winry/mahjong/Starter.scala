package com.winry.mahjong

import akka.actor.{ActorSystem, Props}
import com.winry.mahjong.actor.Server

/**
  * Created by User on 11/25/2016.
  */
object Starter extends App {

  val actorSystem = ActorSystem("server")
  val server = actorSystem.actorOf(Props(new Server("127.0.0.1", 8888)), "front")
}
