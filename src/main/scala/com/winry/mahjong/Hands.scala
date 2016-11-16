package com.winry.mahjong

import com.winry.mahjong.counter.DistanceCounter
import com.winry.mahjong.melds.{Chi, Kan, Pon}
import com.winry.mahjong.util.ListUtil

/**
  * Created by congzhou on 7/29/2016.
  */
class Hands(mahjongs: List[Mahjong]) {
  var freeMahjongs = mahjongs
  var isTen = false
  var isReach = false
  //是否鸣牌
  var isClosed = true
  var chis: List[Chi] = Nil
  var pons: List[Pon] = Nil
  var kans: List[Kan] = Nil

  def push(mahjong: Mahjong): Unit = {
    freeMahjongs = freeMahjongs ::: List(mahjong)
    freeMahjongs = freeMahjongs.sorted
    if (getDistance == 0) isTen = true
  }

  /**
    *
    * @param toDiscard index of mahjong to discard
    */
  def discard(toDiscard: Int): Unit = {
    freeMahjongs = freeMahjongs.take(toDiscard) ++ freeMahjongs.drop(toDiscard + 1)
  }

  def canChi(mahjong: Mahjong): Boolean = {
    val typ = mahjong.typ
    val num = mahjong.num
    (freeMahjongs.contains(Mahjong(typ, num - 2)) && freeMahjongs.contains(Mahjong(typ, num - 1))) ||
      (freeMahjongs.contains(Mahjong(typ, num - 1)) && freeMahjongs.contains(Mahjong(typ, num + 1))) ||
      (freeMahjongs.contains(Mahjong(typ, num + 1)) && freeMahjongs.contains(Mahjong(typ, num + 2)))
  }

  def chi(mahjong: Mahjong, index1: Int, index2: Int): Unit = {
    chis = chis ::: List(new Chi(List(mahjong, freeMahjongs(index1), freeMahjongs(index2)).sorted, false))
    discard(index1)
    discard(index2)
  }

  def canPon(mahjong: Mahjong): Boolean = {
    freeMahjongs.count(_ == mahjong) >= 2
  }

  def pon(mahjong: Mahjong): Unit = {
    pons = pons ::: List(new Pon(List(mahjong, mahjong, mahjong), false))
    freeMahjongs = ListUtil.removeElement(freeMahjongs, mahjong)
    freeMahjongs = ListUtil.removeElement(freeMahjongs, mahjong)
  }

  def kan(mahjong: Mahjong): Unit = ???

  def getDistance: Int = {
    new DistanceCounter(this).countDistance
  }

}
