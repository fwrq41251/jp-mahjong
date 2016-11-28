package com.winry.mahjong.handler

import akka.actor.Actor
import com.winry.mahjong.{Lobby, Session}
import com.winry.mahjong.handler.LoginHandler.Login
import com.winry.mahjong.message.{LoginReq, LoginResp}
import com.winry.mahjong.service.UserService

/**
  * Created by User on 11/25/2016.
  */
class LoginHandler extends Actor {

  override def receive: Receive = {
    case Login(session, loginReq) => login(session, loginReq)
  }

  private def login(session: Session, loginReq: LoginReq): Unit = {
    val user = UserService.findUserByName(loginReq.name)
    Lobby.join(session, user)
    session.userId = user.id
    session.send(LoginResp(session.id))
  }
}

object LoginHandler {

  case class Login(session: Session, loginReq: LoginReq)

}
