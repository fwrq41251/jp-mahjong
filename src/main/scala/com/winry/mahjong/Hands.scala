package com.winry.mahjong

import com.winry.mahjong.counter.DistanceCounter

/**
  * Created by congzhou on 7/29/2016.
  */
class Hands(var mahjongs: List[Mahjong]) {

  var isTin: Boolean = false
  var isReach: Boolean = false

  def push(toPush: Mahjong): Unit = {
    mahjongs = mahjongs ::: List(toPush)
    mahjongs.sorted
  }

  def pop(toPop: Int): Unit = {
    mahjongs = mahjongs.take(toPop) ++ mahjongs.drop(toPop + 1)
  }

  def getDistance: Int = {
    new DistanceCounter(mahjongs).countDistance
  }

  override def toString = s"Hands($mahjongs)"
}
