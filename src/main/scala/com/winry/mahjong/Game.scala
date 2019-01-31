package com.winry.mahjong

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random

/**
  * Created by User on 11/17/2016.
  */
class Game(users: List[User]) {

  object WaitingStatus extends Enumeration {
    val CHI, PON, KAN = Value
  }

  /**
    * 牌山
    */
  var yama: Yama = new Yama()

  /**
    * 玩家
    */
  val players: ListBuffer[Player] = Random.shuffle(users.map(u => new Player(u.id))).to[ListBuffer]

  val playerMap: mutable.Map[Long, Player] = mutable.Map.empty

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

  var turn: Int = 0

  var waitingMap: mutable.Map[Long, List[WaitingStatus.Value]] = mutable.Map.empty

  def newKyoku(): Unit = {
    this.renchan = renchan
    yama = new Yama()
    players.foreach(p => {
      val mahjongs = for (_ <- 1 to 13) yield yama.take()
      p.init(new Hands(mahjongs.toList))
    })
    kyoku += 1
    if (kyoku % 4 == 1) kaze += 1
    val oya = players.head
    oya.deal(yama.take())
  }

  def reach(userId: Long, toDiscard: Int): Unit = {
    val player = playerMap(userId)
    player.reach(toDiscard)
    reachbou += 1
  }

  def tsumo(userId: Long) = {
    val player = playerMap(userId)
  }

  def discard(userId: Long, toDiscard: Int): Unit = {
    val player = playerMap(userId)
    player.discard(toDiscard)
    val mahjong = player.hands.get(toDiscard)
    for (p <- players.filterNot(_.userId != userId)) {
      val waitingStatus = waitingMap.getOrElse(p.userId, List())
      if (p.hands.canKan(mahjong)) {
        waitingMap += p.userId -> (WaitingStatus.KAN :: waitingStatus)
      }
      if (p.hands.canPon(mahjong)) {
        waitingMap += p.userId -> (WaitingStatus.PON :: waitingStatus)
      }
      if (p.hands.canChi(mahjong)) {
        waitingMap += p.userId -> (WaitingStatus.CHI :: waitingStatus)
      }
    }
    if (waitingMap.isEmpty) {
      players(turn).deal(yama.take())
      turn += 1
    }
  }

  def chi(userId: Long, toChi: Mahjong, index1: Int, index2: Int): Unit = {
    val player = playerMap(userId)
    player.chi(toChi, index1, index2)
    turn += 1
  }

  private def kyokuOwari(): Unit = {

  }

  private def pass(userId: Long) = {

  }
}
