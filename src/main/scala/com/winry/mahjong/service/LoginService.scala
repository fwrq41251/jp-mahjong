package com.winry.mahjong.service

import akka.actor.ActorRef
import com.winry.mahjong.Lobby
import com.winry.mahjong.packet.{LoginReq, LoginResp}

/**
  * Created by User on 11/25/2016.
  */
object LoginService {

  def login(client: ActorRef, loginReq: LoginReq): LoginResp = {
    val user = UserService.findUserByName(loginReq.name)
    val sessionId = Lobby.join(client, user)
    LoginResp(sessionId)
  }
}
