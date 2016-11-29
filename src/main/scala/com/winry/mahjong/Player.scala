package com.winry.mahjong

import scala.collection.mutable.ListBuffer

/**
  * Created by congzhou on 8/15/2016.
  */
class Player(id: Long) {

  val userId: Long = id

  /**
    * 点数
    */
  var point: Int = 250000

  /**
    * 牌河
    */
  val hou: ListBuffer[Mahjong] = ListBuffer.empty

  /**
    * 手牌
    */
  var hands: Hands = _

  def init(hands: Hands): Unit = {
    hou.clear()
    this.hands = hands
  }

  /**
    * 弃牌
    *
    * @param toDiscard
    * @return
    */
  def discard(toDiscard: Int): Unit = {
    val discard = hands.discard(toDiscard)
    hou += discard
  }

  /**
    * 发牌
    *
    * @param mahjong
    */
  def deal(mahjong: Mahjong): Unit = {
    hands.push(mahjong)
  }

  /**
    * 立直
    *
    * @param toDiscard
    */
  def reach(toDiscard: Int): Unit = {
    discard(toDiscard)
    hands.isReach = true
  }
}
