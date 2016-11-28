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
  val userMap: Map[Long, User] = users.map(u => u.id -> u).toMap

  /**
    * 立直棒
    */
  var reachbou: Int = 0

  /**
    * 本场
    */
  var honba: Int = 0

  /**
    * 是否连庄
    */
  var renchan: Boolean = false

  /**
    * 局数
    */
  var kyoku: Int = 1

  /**
    * 场风
    */
  var kaze: Int = 1

  def newKyoku(renchan: Boolean): Unit = {
    this.renchan = renchan
    yama = new Yama()
    players.foreach(p => {
      val mahjongs = for (_ <- 1 to 13) yield yama.take()
      p.init(new Hands(mahjongs.toList))
    })
    val oya = players.head
    oya.deal(yama.take())
    if (renchan) {
      honba += 1
    } else {
      honba = 0
      kyoku += 1
      if (kyoku % 4 == 1) kaze += 1
      players -= oya
      players += oya
    }
  }

}
