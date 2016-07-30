package com.winry.mahjong

/**
  * Created by congzhou on 7/29/2016.
  */
class Hands(var mahjongs: List[Mahjong]) {

  var isTin: Boolean = false
  var isReach: Boolean = false

  def push(): Unit = {

  }

  def pop(): Unit = {

  }

  def getDistance: Int = {
    new MahjongCounter(mahjongs).countDistance
  }

  override def toString = s"Hands($mahjongs)"
}
