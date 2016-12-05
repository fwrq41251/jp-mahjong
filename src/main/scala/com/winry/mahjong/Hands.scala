package com.winry.mahjong

import com.winry.mahjong.counter.DistanceCounter
import com.winry.mahjong.melds.{Chi, Kan, Pon}

import scala.collection.mutable.ListBuffer

/**
  * Created by congzhou on 7/29/2016.
  */
class Hands(mahjongs: List[Mahjong]) {
  var freeMahjongs: ListBuffer[Mahjong] = mahjongs.to[ListBuffer]
  var isTen = false
  var isReach = false
  //是否鸣牌
  var isClosed = true
  var chis: ListBuffer[Chi] = ListBuffer.empty
  var pons: ListBuffer[Pon] = ListBuffer.empty
  var kans: ListBuffer[Kan] = ListBuffer.empty

  def push(mahjong: Mahjong): Unit = {
    freeMahjongs = freeMahjongs += mahjong
    freeMahjongs = freeMahjongs.sorted
    if (getDistance == 0) isTen = true
  }

  /**
    *
    * @param toDiscard index of mahjong to discard
    */
  def discard(toDiscard: Int): Mahjong = {
    freeMahjongs.remove(toDiscard)
  }

  def canChi(mahjong: Mahjong): Boolean = {
    val typ = mahjong.typ
    val num = mahjong.num
    (freeMahjongs.contains(Mahjong(typ, num - 2)) && freeMahjongs.contains(Mahjong(typ, num - 1))) ||
      (freeMahjongs.contains(Mahjong(typ, num - 1)) && freeMahjongs.contains(Mahjong(typ, num + 1))) ||
      (freeMahjongs.contains(Mahjong(typ, num + 1)) && freeMahjongs.contains(Mahjong(typ, num + 2)))
  }

  def chi(mahjong: Mahjong, index1: Int, index2: Int): Unit = {
    chis += new Chi(List(mahjong, freeMahjongs(index1), freeMahjongs(index2)).sorted, false)
    discard(index1)
    discard(index2)
  }

  def canPon(mahjong: Mahjong): Boolean = {
    freeMahjongs.count(_ == mahjong) >= 2
  }

  def pon(mahjong: Mahjong): Unit = {
    pons += new Pon(List(mahjong), false)
    freeMahjongs -= mahjong
    freeMahjongs -= mahjong
  }

  def canKan(mahjong: Mahjong): Boolean = {
    pons.contains(new Pon(List(mahjong))) || freeMahjongs.count(_ == mahjong) >= 3
  }

  def kan(mahjong: Mahjong, isClosed: Boolean): Unit = {
    kans += new Kan(List(mahjong), isClosed)
    if (freeMahjongs.count(_ == mahjong) >= 3) {
      for (_ <- 1 to 3) {
        freeMahjongs -= mahjong
      }
    }
    if (this.isClosed && !isClosed) this.isClosed = false
  }

  def getDistance: Int = {
    new DistanceCounter(this).countDistance
  }

}
