package com.winry.mahjong.actor

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorLogging, Props}
import akka.io.{IO, Tcp}
import com.winry.mahjong.Session

/**
  * one app only have one Server actor.
  */
class Server(host: String, port: Int) extends Actor with ActorLogging {

  var sessionCount = 1L

  import Tcp._
  import context.system

  override def preStart() {
    val opts = List(SO.KeepAlive(on = true), SO.TcpNoDelay(on = true))
    IO(Tcp) ! Bind(self, new InetSocketAddress(host, port), options = opts)
  }

  def receive: Receive = {
    case b@Bound(localAddress) =>

    case CommandFailed(_: Bind) => context stop self

    case c@Connected(remote, local) =>
      val connection = sender()
      val session = new Session(sessionCount, connection)
      sessionCount += 1
      val dispatcher = context.actorOf(Props(new Dispatcher(session)))
      connection ! Register(dispatcher)
  }

}
