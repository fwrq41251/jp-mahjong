package com.winry.mahjong.actor

import java.net.InetSocketAddress
import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, Props}
import akka.io.{IO, Tcp}
import com.winry.mahjong.Session
import com.winry.mahjong.actor.Dispatcher.Clean

import scala.concurrent.duration.Duration

/**
  * One app only have one Server actor.
  */
class Server(host: String, port: Int) extends Actor with ActorLogging {

  var sessionCount = 1L

  import context.dispatcher

  context.system.scheduler.schedule(Duration.create(1, TimeUnit.MINUTES), Duration.create(10, TimeUnit.MINUTES), new Runnable {
    override def run(): Unit = {
      //when server has other children, ask lobby for active session
      val dispatchers = context.children
      log.info("active session:" + dispatchers.size)
      dispatchers.foreach(_ ! Clean)
    }
  })

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
      val dispatcher = context.actorOf(Dispatcher.props(session))
      connection ! Register(dispatcher)
  }

}

object Server {

  def props(host: String, port: Int): Props = Props(new Server(host, port))
}
