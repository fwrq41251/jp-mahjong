package com.winry.mahjong.actor

import akka.actor.{Actor, Props}
import com.winry.mahjong.actor.GameCenter.Disconnect
import com.winry.mahjong.actor.GameController.Reach
import com.winry.mahjong.message.ReachResp
import com.winry.mahjong.{Game, Session}

/**
  * Created by User on 11/29/2016.
  */
class GameController(val sessions: List[Session], val game: Game) extends Actor {

  override def receive: Receive = {
    case Reach(gameId, userId, toDiscard) =>
      game.reach(userId, toDiscard)
      //todo set resp fields
      sessions.foreach(_.send(ReachResp))
    //todo set player offline
    case Disconnect(session) =>
    case _ =>
  }
}

object GameController {

  def props(sessions: List[Session], game: Game): Props = Props(new GameController(sessions, game))

  trait GameCommand {
    def gameId: Long

    def userId: Long
  }

  case class Reach(gameId: Long, userId: Long, toDiscard: Int) extends GameCommand

  case class Tsumo(gameId: Long, userId: Long) extends GameCommand

  case class Discard(gameId: Long, userId: Long, toDiscard: Int) extends GameCommand

}
