package com.winry.mahjong.actor

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorLogging, Props}
import akka.io.{IO, Tcp}

/**
  * Created by User on 11/23/2016.
  */
class Server(host: String, port: Int) extends Actor with ActorLogging {

  import Tcp._
  import context.system

  override def preStart() {
    log.info("Starting tcp net server!")
    val opts = List(SO.KeepAlive(on = true), SO.TcpNoDelay(on = true))
    IO(Tcp) ! Bind(self, new InetSocketAddress(host, port), options = opts)
  }


  def receive: Receive = {
    case b@Bound(localAddress) =>
      log.info("server is listening on:" + localAddress)

    case CommandFailed(_: Bind) => context stop self

    case c@Connected(remote, local) =>
      val dispatcher = context.actorOf(Props[Dispatcher])
      val connection = sender()
      connection ! Register(dispatcher)
  }

}
