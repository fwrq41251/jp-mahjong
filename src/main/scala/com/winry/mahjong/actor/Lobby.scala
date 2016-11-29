package com.winry.mahjong.actor

import akka.actor.Actor
import com.winry.mahjong.actor.Lobby.{Login, Ready}
import com.winry.mahjong.message.{GameStartResp, LoginReq, LoginResp}
import com.winry.mahjong.service.UserService
import com.winry.mahjong.{Game, Session, User}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by User on 11/25/2016.
  */
class Lobby extends Actor {

  private val sessionMap = mutable.Map.empty[Session, User]

  val games: ListBuffer[Game] = ListBuffer.empty
  val readyUsers: ListBuffer[Session] = ListBuffer.empty

  override def receive: Receive = {
    case Login(session, loginReq) => login(session, loginReq)
    case Ready(session) => handleReady(session)
    case _ => throw new IllegalArgumentException("unknown message for lobby!")
  }

  private def login(session: Session, loginReq: LoginReq): Unit = {
    val user = UserService.findUserByName(loginReq.name)
    sessionMap += session -> user
    session.userId = user.id
    session.send(LoginResp(session.id))
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

object Lobby {

  case class Ready(session: Session)

  case class Login(session: Session, loginReq: LoginReq)

}
