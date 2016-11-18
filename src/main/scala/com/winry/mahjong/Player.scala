package com.winry.mahjong

import scala.collection.mutable.ListBuffer

/**
  * Created by congzhou on 8/15/2016.
  */
class Player(id: String) {

  val userId: String = id

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

  def init(hands: Hands) = {
    hou.clear()
    this.hands = hands
  }

  def discard(toDiscard: Int) = {
    val discard = hands.discard(toDiscard)
    hou += discard
  }

  def deal(mahjong: Mahjong) = {
    hands.push(mahjong)
  }
}
