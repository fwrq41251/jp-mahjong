package com.winry.mahjong.actor

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorLogging, Props}
import akka.io.{IO, Tcp}

/**
  * Created by User on 11/23/2016.
  */
class Server(host: String, port: Int) extends Actor with ActorLogging {

  import akka.io.Tcp._

  override def preStart() {
    log.info("Starting tcp net server!")
    val opts = List(SO.KeepAlive(on = true), SO.TcpNoDelay(on = true))
    IO(Tcp) ! Bind(self, new InetSocketAddress(host, port), options = opts)
  }


  def receive: Receive = {
    case b@Bound(localAddress) =>
    // do some logging or setup ...

    case CommandFailed(_: Bind) => context stop self

    case c@Connected(remote, local) =>
      val handler = context.actorOf(Props[GameHandler])
      val connection = sender()
      connection ! Register(handler)
  }

}
