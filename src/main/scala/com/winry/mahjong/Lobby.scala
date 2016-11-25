package com.winry.mahjong

import akka.actor.ActorRef

import scala.collection.mutable

/**
  * Created by User on 11/25/2016.
  */
object Lobby {

  var currentSessionId = 1L
  private val sessionMap = mutable.Map.empty[Session, User]

  def join(client:ActorRef, user: User): Long = {
    val session = new Session(currentSessionId, client)
    sessionMap += session -> user
    currentSessionId += 1
    currentSessionId
  }
}
