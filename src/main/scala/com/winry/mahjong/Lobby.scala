package com.winry.mahjong

import scala.collection.mutable

/**
  * Created by User on 11/25/2016.
  */
object Lobby {

  private val sessionMap = mutable.Map.empty[Session, User]

  def join(session: Session, user: User): Unit = {
    sessionMap += session -> user
  }
}
