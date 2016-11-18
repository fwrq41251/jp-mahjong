package com.winry.mahjong

import scala.collection.mutable.ListBuffer
import scala.util.Random

/**
  * Created by User on 11/17/2016.
  */
class Game(users: List[User]) {

  /**
    * 牌山
    */
  var yama: Yama = new Yama()

  /**
    * 玩家
    */
  val players: ListBuffer[Player] = Random.shuffle(users.map(u => new Player(u.id))).to[ListBuffer]

  /**
    * 用户
    */
  val userMap: Map[String, User] = users.map(u => u.id -> u).toMap

  /**
    * 立直棒
    */
  val reachbou: Int = 0

  /**
    * 本场
    */
  val honba: Int = 0

  /**
    * 是否连庄
    */
  val renchan: Boolean = false

  /**
    * 局数
    */
  val kyoku: Int = 1

  /**
    * 场风
    */
  val kaze: Int = 1

  def newKyoku() = {
    yama = new Yama()
    players.foreach(p => {
      val mahjongs = for (i <- 1 to 13) yield yama.take()
      p.init(new Hands(mahjongs.toList))
    })
    players.head.deal(yama.take())
  }

}
