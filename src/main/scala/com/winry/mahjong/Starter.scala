package com.winry.mahjong

import akka.actor.{ActorSystem, Props}
import com.winry.mahjong.actor.{Lobby, Server}
import com.winry.mahjong.util.{ConfigUtil, ServerRegister}

/**
  * Created by User on 11/25/2016.
  */
object Starter extends App {

  val env = args(0)
  ConfigUtil.env = env

  val actorSystem = ActorSystem("server")
  val serverConfig = ConfigUtil.getServerConfig
  private val port = serverConfig.port
  val server = actorSystem.actorOf(Server.props(serverConfig.host, port), "front")
  val lobby = actorSystem.actorOf(Props[Lobby], "lobby")
  val gameCenter = actorSystem.actorOf(Props[Lobby], "game")

  ServerRegister.register(port)
}
