package com.winry.mahjong.actor

import java.util.{Calendar, Date}

import akka.actor.{Actor, ActorLogging, ActorSelection, PoisonPill, Props}
import akka.io.Tcp.{PeerClosed, Received}
import com.winry.mahjong.Session
import com.winry.mahjong.actor.Dispatcher.Clean
import com.winry.mahjong.actor.GameController.{Discard, Reach, Tsumo}
import com.winry.mahjong.actor.Lobby.{Login, Logout, Ready}
import com.winry.mahjong.message.PacketMSG
import com.winry.mahjong.message.PacketMSG.Msg

/**
  * Every connection has a separated dispatcher that holds a unique session.
  */
class Dispatcher(val session: Session) extends Actor with ActorLogging {

  val lobby: ActorSelection = context.actorSelection("akka://server/user/lobby")
  val gameController: ActorSelection = context.actorSelection("akka://server/user/game")
  val calender: Calendar = Calendar.getInstance()
  var lastOperateTime: Date = calender.getTime
  val timeout: Int = 15 * 60 * 1000

  override def receive: Receive = {
    case Received(data) =>
      lastOperateTime = calender.getTime
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
    case Clean =>
      if(calender.getTime.getTime - lastOperateTime.getTime > timeout) {
        lobby ! Logout(session)
        self ! PoisonPill
      }
    case PeerClosed => context stop self
  }

}

object Dispatcher {

  def props(session: Session): Props = Props(new Dispatcher(session))
  case class Clean()
}
