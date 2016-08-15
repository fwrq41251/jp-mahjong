package com.winry.mahjong

import com.winry.mahjong.counter.DistanceCounter
import com.winry.mahjong.melds.{Chi, Pon}

/**
  * Created by congzhou on 7/29/2016.
  */
class Hands(mahjongs0: List[Mahjong]) {
  var mahjongs = mahjongs0
  var isTen = false
  var isReach = false
  var isClosed = true
  var chis: List[Chi] = Nil
  var pons: List[Pon] = Nil

  def push(mahjong: Mahjong): Unit = {
    mahjongs = mahjongs ::: List(mahjong)
    mahjongs = mahjongs.sorted
    if (getDistance == 0) isTen = true
  }

  /**
    *
    * @param toDiscard index of mahjong to discard
    */
  def discard(toDiscard: Int): Unit = {
    mahjongs = mahjongs.take(toDiscard) ++ mahjongs.drop(toDiscard + 1)
  }

  def canChi(mahjong: Mahjong):Boolean = {
    case mahjong(_, 1) => mahjongs.contains()
    case mahjong(_, 2) => mahjongs.contains()
    case mahjong(_, 8) => mahjongs.contains()
    case mahjong(_, 9) => mahjongs.contains()
    case _ => mahjongs.
  }

  def chi(mahjong: Mahjong): Unit = {

  }

  def canPon(mahjong: Mahjong):Boolean = {

  }

  def pon(mahjong: Mahjong): Unit = {

  }

  def kan(mahjong: Mahjong): Unit = ???

  def getDistance: Int = {
    new DistanceCounter(mahjongs).countDistance
  }

  override def toString = s"Hands($mahjongs)"
}
