package com.winry.mahjong.counter

import com.winry.mahjong.{Mahjong, Types}
import com.winry.mahjong.checker.{ChowChecker, PonChecker, RideChecker}

/**
  * Created by congzhou on 7/29/2016.
  */
class DistanceCounter(mahjongs: List[Mahjong]) extends ChowChecker with PonChecker with RideChecker {

  implicit def convert[Mahjong, CountMahjong <% Mahjong](l: List[CountMahjong]): List[Mahjong] = l map { a => a: Mahjong }

  var countMahjongs: List[CountMahjong] = mahjongs.map(m => new CountMahjong(m)): List[CountMahjong]

  def countDistance: Int = {
    val distance = 8
    distance - (countChows + countPons) * 2 - countEyes - countRides
  }

  private def count(size: Int, predicate: List[Mahjong] => Boolean, toCount: List[CountMahjong]): Int = {
    var count = 0
    var temp = toCount
    while (temp.size >= size) {
      val toCount = temp.take(size)
      if (predicate(toCount)) {
        count = count + 1
        toCount.foreach(m => m.isCount = true)
      }
      temp = temp.drop(1)
    }
    count
  }

  def countChows: Int = {
    count(3, isChow, countMahjongs.filter(m => m.typ != Types.Word).distinct)
  }

  def countPons: Int = {
    count(3, isPon, countMahjongs.filter(m => !m.isCount))
  }

  def countEyes: Int = {
    var count = 0
    var temp = countMahjongs.filter(m => !m.isCount)
    while (temp.size >= 2 && count == 0) {
      val toCount = temp.take(2)
      if (isEye(toCount)) {
        count = 1
        toCount.foreach(m => m.isCount = true)
      }
      temp = temp.drop(1)
    }
    count
  }


  def countRides: Int = {
    count(2, isRide, countMahjongs.filter(m => !m.isCount))
  }

}
