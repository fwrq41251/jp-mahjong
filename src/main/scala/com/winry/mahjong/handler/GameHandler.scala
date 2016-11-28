package com.winry.mahjong.handler

import akka.actor.Actor
import com.winry.mahjong.handler.GameHandler.Ready
import com.winry.mahjong.message.GameStartResp
import com.winry.mahjong.service.UserService
import com.winry.mahjong.{Game, Session}

import scala.collection.mutable.ListBuffer

/**
  * Created by User on 11/28/2016.
  */
class GameHandler extends Actor {

  val games: ListBuffer[Game] = ListBuffer.empty
  val readyUsers: ListBuffer[Session] = ListBuffer.empty

  override def receive: Receive = {
    case Ready(session) => handleReady(session)
  }

  private def handleReady(session: Session): Unit = {
    readyUsers += session
    if (readyUsers.size == 4) {
      val users = readyUsers.map(s => UserService.findUserById(s.userId)).toList
      val game = new Game(users)
      games += game
      //todo set game start resp fields
      readyUsers.foreach(_.send(GameStartResp))
      readyUsers.clear()
    }
  }
}

object GameHandler {

  case class Ready(session: Session)

}
