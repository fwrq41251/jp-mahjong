package com.winry.mahjong

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import com.winry.mahjong.actor.{Lobby, Server}
import com.winry.mahjong.util.ServerRegister

/**
  * Created by User on 11/25/2016.
  */
object Starter extends App {

  val env = args(0)

  val actorSystem = ActorSystem("server")
  val serverConfig = ConfigFactory.load().getConfig("server").getConfig(env)
  val host = serverConfig.getString("host")
  val port = serverConfig.getInt("port")
  val server = actorSystem.actorOf(Server.props(host, port), "front")
  val lobby = actorSystem.actorOf(Props[Lobby], "lobby")
  val gameCenter = actorSystem.actorOf(Props[Lobby], "game")

  ServerRegister.register(env, port)
}
