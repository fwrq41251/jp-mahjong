package com.winry.mahjong.counter

import com.winry.mahjong.Mahjong
import com.winry.mahjong.checker.{ChowChecker, PonChecker, RideChecker}

/**
  * Created by congzhou on 7/29/2016.
  */
class DistanceCounter(var mahjongs: List[Mahjong]) extends ChowChecker with PonChecker with RideChecker {

  var uncountMahjongs: List[Mahjong] = mahjongs

  def countDistance: Int = {
    val distance = 8
    distance - (countChows + countPons) * 2 - countEyes - countRides
  }

  private def count(size: Int, predicate: List[Mahjong] => Boolean): Int = {
    var count: Int = 0
    var temp = uncountMahjongs
    while (temp.size >= size) {
      val toCount = temp.take(size)
      if (predicate(toCount)) {
        count = count + 1
        uncountMahjongs = removeSubList(uncountMahjongs, toCount)
      }
      temp = temp.drop(1)
    }
    count
  }

  def countChows: Int = {
    count(3, isChow)
  }

  def countPons: Int = {
    count(3, isPon)
  }

  def countEyes: Int = {
    count(2, isEye)
  }


  def countRides: Int = {
    count(2, isRide)
  }

  def removeSubList[A](l: List[A], sublist: List[A]): List[A] = l.indexOfSlice(sublist) match {
    case -1 => l
    case index => removeSubList(l.patch(index, Nil, sublist.length), sublist)
  }
}
