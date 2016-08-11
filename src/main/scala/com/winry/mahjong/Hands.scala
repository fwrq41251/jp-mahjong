package com.winry.mahjong

import com.winry.mahjong.counter.DistanceCounter

/**
  * Created by congzhou on 7/29/2016.
  */
class Hands(mahjongs0: List[Mahjong]) {
  var mahjongs = mahjongs0
  var isTin = false
  var isReach = false
  var isClosed = true

  def push(toPush: Mahjong): Unit = {
    mahjongs = mahjongs ::: List(toPush)
    mahjongs = mahjongs.sorted
  }

  def pop(toPop: Int): Unit = {
    mahjongs = mahjongs.take(toPop) ++ mahjongs.drop(toPop + 1)
  }

  def getDistance: Int = {
    new DistanceCounter(mahjongs).countDistance
  }

  override def toString = s"Hands($mahjongs)"
}
