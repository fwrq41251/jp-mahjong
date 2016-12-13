package com.winry.mahjong.actor

import akka.actor.{Actor, ActorSelection}
import com.winry.mahjong.actor.GameCenter.{Reconnect, StartGame}
import com.winry.mahjong.actor.Lobby.{Login, Logout, Ready}
import com.winry.mahjong.message.{LoginReq, LoginResp}
import com.winry.mahjong.service.UserService
import com.winry.mahjong.util.{Redis, ServerRegister}
import com.winry.mahjong.{Session, User}

import scala.collection.mutable

/**
  * Created by User on 11/25/2016.
  */
class Lobby extends Actor {

  val sessionMap: mutable.Map[Session, User] = mutable.Map.empty
  val readyUsers: mutable.Set[Session] = mutable.Set.empty
  val gameCenter: ActorSelection = context.actorSelection("akka://server/user/game")

  override def receive: Receive = {
    case Login(session, loginReq) => login(session, loginReq)
    case Ready(session) => handleReady(session)
    case Logout(session) => handleLogout(session)
  }

  private def handleLogout(session: Session) = {
    sessionMap -= session
    readyUsers -= session
    saveSessionCount(sessionMap.size)
  }

  private def login(session: Session, loginReq: LoginReq): Unit = {
    val user = UserService.findUserByName(loginReq.name)
    sessionMap += session -> user
    session.userId = user.id
    session.send(LoginResp(session.id))
    saveSessionCount(sessionMap.size)
    gameCenter ! Reconnect(session)
  }

  private def handleReady(session: Session): Unit = {
    readyUsers += session
    if (readyUsers.size == 4) {
      val users = readyUsers.map(s => UserService.findUserById(s.userId)).toList
      gameCenter ! StartGame(readyUsers.toList, users)
      readyUsers.clear()
    }
  }

  private def saveSessionCount(count: Int) = {
    import resource._
    for (client <- managed(Redis.getResource)) {
      client.set("server:" + ServerRegister.hostPort, count.toString)
    }
  }

}

object Lobby {

  case class Ready(session: Session)

  case class Login(session: Session, loginReq: LoginReq)

  case class Logout(session: Session)

}
