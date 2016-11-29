package com.winry.mahjong.actor

import akka.actor.Actor
import com.winry.mahjong.actor.GameController.{Reach, StartGame}
import com.winry.mahjong.message.GameStartResp
import com.winry.mahjong.{Game, Session, User}

import scala.collection.mutable

/**
  * Created by User on 11/29/2016.
  */
class GameController extends Actor {

  var currentGameId = 1L
  val gameMap: mutable.Map[Long, Game] = mutable.Map.empty

  override def receive: Receive = {
    case StartGame(sessions, users) =>
      val game = new Game(users)
      gameMap += currentGameId -> game
      currentGameId += 1
      sessions.foreach(_.send(GameStartResp))
    case Reach(gameId, userId, toDiscard) =>
      val game = gameMap(gameId)
      game.reach(userId, toDiscard)
    case _ =>
  }
}

object GameController {

  case class StartGame(sessions: List[Session], users: List[User])

  case class Reach(gameId: Long, userId: Long, toDiscard: Int)

  case class Tsumo(gameId: Long, userId: Long)

  case class Discard(gameId: Long, userId: Long, toDiscard: Int)

}
