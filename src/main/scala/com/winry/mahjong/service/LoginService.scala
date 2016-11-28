package com.winry.mahjong.service

import com.winry.mahjong.message.{LoginReq, LoginResp}
import com.winry.mahjong.{Lobby, Session}

/**
  * Created by User on 11/25/2016.
  */
object LoginService {

  def login(session: Session, loginReq: LoginReq): LoginResp = {
    val user = UserService.findUserByName(loginReq.name)
    Lobby.join(session, user)
    LoginResp(session.id)
  }
}
