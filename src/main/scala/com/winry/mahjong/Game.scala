package com.winry.mahjong

/**
  * Created by User on 11/17/2016.
  */
class Game(users: List[User]) {

  /**
    * 牌山
    */
  val yama: Yama = new Yama()

  /**
    * 玩家
    */
  val players: Map[User, Player] = users.map(u => u -> new Player()).toMap

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


}
