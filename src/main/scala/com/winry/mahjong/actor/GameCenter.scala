package com.winry.mahjong.actor

import akka.actor.{Actor, ActorRef}
import com.winry.mahjong.actor.GameCenter.{Disconnect, Reconnect, StartGame}
import com.winry.mahjong.actor.GameController.GameCommand
import com.winry.mahjong.message.GameStartResp
import com.winry.mahjong.{Game, Session, User}

import scala.collection.mutable

/**
  * Created by User on 11/29/2016.
  */
class GameCenter extends Actor {

  var currentGameId = 1L
  /**
    * gameId -> gameController
    */
  val gameControllerMap: mutable.Map[Long, ActorRef] = mutable.Map.empty
  val sessionMap: mutable.Map[Session, ActorRef] = mutable.Map.empty
  val droppedSessionMap: mutable.Map[Long, ActorRef] = mutable.Map.empty

  override def receive: Receive = {
    case StartGame(sessions, users) =>
      val game = new Game(users)
      val gameController = context.actorOf(GameController.props(sessions, game))
      gameControllerMap += currentGameId -> gameController
      sessions.foreach(s => {
        sessionMap += s -> gameController
        s.send(GameStartResp)
        s.gameId = currentGameId
      })
      currentGameId += 1
    case gameCommand: GameCommand =>
      val gameController = gameControllerMap(gameCommand.gameId)
      gameController ! gameCommand
    case Disconnect(session) =>
      sessionMap.get(session).foreach(c => {
        droppedSessionMap += session.userId -> c
        sessionMap -= session
        c ! Disconnect
      })
    case Reconnect(session) =>
      droppedSessionMap.get(session.userId).foreach(c => {
        droppedSessionMap -= session.userId
        sessionMap += session -> c
        c ! Reconnect(session)
      })
    case _ =>

  }
}

object GameCenter {

  case class StartGame(sessions: List[Session], users: List[User])

  case class Disconnect(session: Session)

  case class Reconnect(session: Session)

}

