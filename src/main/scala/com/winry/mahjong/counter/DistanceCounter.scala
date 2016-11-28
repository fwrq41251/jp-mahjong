package com.winry.mahjong.counter

import com.winry.mahjong._
import com.winry.mahjong.checker.{ChiChecker, PonChecker, RideChecker}

/**
  * Created by congzhou on 7/29/2016.
  */
class DistanceCounter(hands: Hands) extends ChiChecker with PonChecker with RideChecker {

  implicit def convert(l: List[CountMahjong]): List[Mahjong] = l map { a => a: Mahjong }

  val countMahjongs: List[CountMahjong] = hands.freeMahjongs.toList.map(new CountMahjong(_))

  def countDistance: Int = {
    if (hands.isClosed) {
      Math.min(8 - (countChis + countPons) * 2 - hasEyes - countRides, 6 - countEyes)
    }
    else {
      8 - (hands.chis.size + hands.pons.size + hands.kans.size + countChis + countPons) * 2 - hasEyes
    }
  }

  private def count(size: Int, predicate: List[Mahjong] => Boolean, toCount: List[CountMahjong]): Int = {
    var count = 0
    var temp = toCount
    while (temp.size >= size) {
      val toCount = temp.take(size)
      if (predicate(toCount)) {
        count += 1
        toCount.foreach(m => m.isCount = true)
        temp = temp.drop(3)
      } else {
        temp = temp.tail
      }
    }
    count
  }

  def countChis: Int = {
    val result = count(3, isChi, countMahjongs.filter(!_.isCount).filter(m => m.typ != Types.Word).distinct)
    if (result == 0) result else result + countChis
  }

  def countPons: Int = {
    count(3, isPon, countMahjongs.filter(!_.isCount))
  }

  def countEyes: Int = {
    count(2, isEye, countMahjongs)
  }

  def countRides: Int = {
    count(2, isRide, countMahjongs.filter(!_.isCount))
  }

  def hasEyes: Int = {
    var temp = countMahjongs.filter(!_.isCount)
    while (temp.size >= 2) {
      val toCount = temp.take(2)
      if (isEye(toCount)) {
        toCount.foreach(m => m.isCount = true)
        return 1
      } else {
        temp = temp.tail
      }
    }
    0
  }

}
