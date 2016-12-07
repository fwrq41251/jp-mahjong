package com.winry.mahjong.actor

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, ActorSelection, Cancellable, PoisonPill, Props}
import akka.io.Tcp.{PeerClosed, Received}
import com.winry.mahjong.Session
import com.winry.mahjong.actor.GameController.{Discard, Reach, Tsumo}
import com.winry.mahjong.actor.Lobby.{Login, Ready}
import com.winry.mahjong.message.PacketMSG
import com.winry.mahjong.message.PacketMSG.Msg

import scala.concurrent.duration.Duration

/**
  * Every connection has a separated dispatcher that holds a unique session.
  */
class Dispatcher(val session: Session) extends Actor with ActorLogging {

  val lobby: ActorSelection = context.actorSelection("akka://server/user/lobby")
  val gameController: ActorSelection = context.actorSelection("akka://server/user/game")
  var killSelf: Cancellable = startSchedule()

  private def startSchedule(): Cancellable = {
    import context.dispatcher
    context.system.scheduler.scheduleOnce(Duration.create(5, TimeUnit.SECONDS), self, PoisonPill)
  }

  override def receive: Receive = {
    case Received(data) =>
      restartSchedule
      log.debug("receive:" + data)
      val packetMessage = PacketMSG.parseFrom(data.toArray)
      packetMessage.msg match {
        case Msg.LoginReq(l) => lobby ! Login(session, l)
        case Msg.ReadyReq(_) => lobby ! Ready(session)
        case Msg.ReachReq(r) => gameController ! Reach(r.gameId, r.userId, r.toDiscard)
        case Msg.TsumoReq(t) => gameController ! Tsumo(t.gameId, t.userId)
        case Msg.DiscardReq(d) => gameController ! Discard(d.gameId, d.userId, d.toDiscard)
        case _ =>
      }
    case PeerClosed => context stop self
  }

  private def restartSchedule = {
    killSelf.cancel()
    killSelf = startSchedule()
  }
}

object Dispatcher {

  def props(session: Session): Props = Props(new Dispatcher(session))
}
