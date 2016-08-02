package com.winry.mahjong.counter

import com.winry.mahjong._
import com.winry.mahjong.checker.{ChowChecker, PonChecker, RideChecker}

/**
  * Created by congzhou on 7/29/2016.
  */
class DistanceCounter(mahjongs: List[Mahjong]) extends ChowChecker with PonChecker with RideChecker {

  implicit def convert[Mahjong, CountMahjong <% Mahjong](l: List[CountMahjong]): List[Mahjong] = l map { a => a: Mahjong }

  var countMahjongs: List[CountMahjong] = mahjongs.map(m => new CountMahjong(m)): List[CountMahjong]

  def countDistance: Int = {
    val distance = 8
    distance - (countChows + countPons) * 2 - hasEyes - countRides
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

  def countChows: Int = {
    val result = count(3, isChow, countMahjongs.filter(m => !m.isCount).filter(m => m.typ != Types.Word).distinct)
    result match {
      case 0 => result
      case _ => result + countChows
    }
  }

  def countPons: Int = {
    count(3, isPon, countMahjongs.filter(m => !m.isCount))
  }

  def countEyes: Int = {
    count(2, isEye, countMahjongs)
  }

  def countRides: Int = {
    count(2, isRide, countMahjongs.filter(m => !m.isCount))
  }

  def hasEyes: Int = {
    var temp = countMahjongs.filter(m => !m.isCount)
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
