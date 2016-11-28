package com.winry.mahjong

import akka.actor.ActorRef

import scala.collection.mutable

/**
  * Created by User on 11/25/2016.
  */
class Session(val id: Long, connect: ActorRef) {

  private val client = connect

  var userId = 0L

  private val sessionVariables = mutable.Map.empty[String, Any]

  def send(message: Any): Unit = {
    client ! message
  }

  def setVariable(name: String, value: Any): Unit = {
    sessionVariables += name -> value
  }

  def getVariable[A](name: String): A = {
    sessionVariables(name).asInstanceOf[A]
  }

}
