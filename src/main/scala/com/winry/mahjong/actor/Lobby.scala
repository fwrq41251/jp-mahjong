package com.winry.mahjong.actor

import akka.actor.{Actor, ActorSelection}
import com.winry.mahjong.actor.GameCenter.StartGame
import com.winry.mahjong.actor.Lobby.{Login, Ready}
import com.winry.mahjong.message.{LoginReq, LoginResp}
import com.winry.mahjong.service.UserService
import com.winry.mahjong.{Session, User}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by User on 11/25/2016.
  */
class Lobby extends Actor {

  val sessionMap: mutable.Map[Session, User] = mutable.Map.empty
  val readyUsers: ListBuffer[Session] = ListBuffer.empty
  val gameController: ActorSelection = context.actorSelection("akka://server/user/game")

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
      gameController ! StartGame(readyUsers.toList, users)
      readyUsers.clear()
    }
  }

}

object Lobby {

  case class Ready(session: Session)

  case class Login(session: Session, loginReq: LoginReq)

}
