package com.winry.mahjong.handler

import com.winry.mahjong.message.{LoginReq, LoginResp}
import com.winry.mahjong.service.UserService
import com.winry.mahjong.{Lobby, Session}

/**
  * Created by User on 11/25/2016.
  */
object LoginHandler {

  def login(session: Session, loginReq: LoginReq): Unit = {
    val user = UserService.findUserByName(loginReq.name)
    Lobby.join(session, user)
    session.userId = user.id
    session.send(LoginResp(session.id))
  }
}
