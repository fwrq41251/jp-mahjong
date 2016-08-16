package com.winry.mahjong

import com.winry.mahjong.counter.DistanceCounter
import com.winry.mahjong.melds.{Chi, Pon}
import com.winry.mahjong.util.ListUtil

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

  def canChi(mahjong: Mahjong): Boolean = {
    case Mahjong(typ, 1) => mahjongs.contains(Mahjong(typ, 2)) && mahjongs.contains(Mahjong(typ, 3))
    case Mahjong(typ, 2) => (mahjongs.contains(Mahjong(typ, 1)) && mahjongs.contains(Mahjong(typ, 3))) ||
      (mahjongs.contains(Mahjong(typ, 3)) && mahjongs.contains(Mahjong(typ, 4)))
    case Mahjong(typ, 8) => (mahjongs.contains(Mahjong(typ, 7)) && mahjongs.contains(Mahjong(typ, 9))) ||
      (mahjongs.contains(Mahjong(typ, 6)) && mahjongs.contains(Mahjong(typ, 7)))
    case Mahjong(typ, 9) => mahjongs.contains(Mahjong(typ, 7)) && mahjongs.contains(Mahjong(typ, 8))
    case Mahjong(typ, num) => (mahjongs.contains(Mahjong(typ, num - 2)) && mahjongs.contains(Mahjong(typ, num - 1))) ||
      (mahjongs.contains(Mahjong(typ, num - 1)) && mahjongs.contains(Mahjong(typ, num + 1))) ||
      (mahjongs.contains(Mahjong(typ, num + 1)) && mahjongs.contains(Mahjong(typ, num + 2)))
  }

  def chi(mahjong: Mahjong): Unit = {

  }

  def canPon(mahjong: Mahjong): Boolean = {
    var temp = mahjongs
    if (temp.contains(mahjong)) {
      temp = ListUtil.removeSubList(temp, List(mahjong))
      temp.contains(mahjong)
    } else false
  }

  def pon(mahjong: Mahjong): Unit = {

  }

  def kan(mahjong: Mahjong): Unit = ???

  def getDistance: Int = {
    new DistanceCounter(mahjongs).countDistance
  }

  override def toString = s"Hands($mahjongs)"
}
