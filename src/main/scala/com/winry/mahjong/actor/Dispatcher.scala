package com.winry.mahjong.actor

import akka.actor.{Actor, ActorLogging, ActorSelection, Props}
import akka.io.Tcp.{PeerClosed, Received}
import com.winry.mahjong.Session
import com.winry.mahjong.actor.GameCenter.Disconnect
import com.winry.mahjong.actor.GameController.{Discard, Reach, Tsumo}
import com.winry.mahjong.actor.Lobby.{Login, Logout, Ready}
import com.winry.mahjong.message.PacketMSG
import com.winry.mahjong.message.PacketMSG.Msg

/**
  * Every connection has a separated dispatcher that holds a unique session.
  */
class Dispatcher(val session: Session) extends Actor with ActorLogging {

  val lobby: ActorSelection = context.actorSelection("akka://server/user/lobby")
  val gameCenter: ActorSelection = context.actorSelection("akka://server/user/game")

  override def receive: Receive = {
    case Received(data) =>
      log.debug("receive:" + data.utf8String)
      val packetMessage = PacketMSG.parseFrom(data.toArray.tail)
      packetMessage.msg match {
        case Msg.LoginReq(l) => lobby ! Login(session, l)
        case Msg.ReadyReq(_) => lobby ! Ready(session)
        case Msg.ReachReq(r) => gameCenter ! Reach(session.gameId, session.userId, r.toDiscard)
        case Msg.TsumoReq(t) => gameCenter ! Tsumo(session.gameId, session.userId)
        case Msg.DiscardReq(d) => gameCenter ! Discard(session.gameId, session.userId, d.toDiscard)
        case _ =>
      }
    case PeerClosed =>
      log.debug("client disconnected")
      lobby ! Logout(session)
      gameCenter ! Disconnect(session)
      context stop self
  }

}

object Dispatcher {

  def props(session: Session): Props = Props(new Dispatcher(session))
}
