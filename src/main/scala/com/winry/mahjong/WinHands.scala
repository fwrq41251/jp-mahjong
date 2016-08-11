package com.winry.mahjong

import com.winry.mahjong.checker.{ChowChecker, PonChecker, RideChecker}
import com.winry.mahjong.counter.CountMahjong
import com.winry.mahjong.melds.{Chow, Eye, Pon, Ride}

import scala.collection.mutable.ListBuffer

/**
  * Created by congzhou on 8/1/2016.
  */
class WinHands(val chows: List[Chow], val pons: List[Pon], val eye: Eye, val ride: Ride, val win: Mahjong, val isReach: Boolean, val isClosed:Boolean) extends ChowChecker with PonChecker {

  var yakuCount = 0

  def add(value: Int): Unit = {
    yakuCount += value
  }

}

object WinHands extends ChowChecker with PonChecker with RideChecker {

  implicit def convert(l: List[CountMahjong]): List[Mahjong] = l map { a => a: Mahjong }

  def getMelds[A](toCount: List[CountMahjong], predicate: List[Mahjong] => Boolean, apply: List[Mahjong] => A) = {
    val buffer = ListBuffer.empty[A]
    var temp = toCount
    while (temp.size >= 3) {
      val toCount = temp.take(3)
      if (predicate(toCount)) {
        toCount.foreach(m => m.isCount = true)
        temp = temp.drop(3)
        buffer += apply(toCount)
      } else {
        temp = temp.tail
      }
    }
    buffer.toList
  }

  def getRide(toCount: List[Mahjong], win: Mahjong): Ride = {
    var temp = toCount
    while (temp.size >= 3) {
      val toCheck = temp.take(3)
      if (!isChow(toCheck) && !isPon(toCheck)) {
        if (toCheck.head == toCheck(1)) {
          if (toCheck.head == win) return new Ride(toCheck.take(2))
        } else {
          return new Ride(toCheck.take(2))
        }
        temp = temp.drop(2)
      }
      else temp = temp.drop(3)
    }
    null
  }

  def apply(hands: Hands, win: Mahjong): WinHands = {
    val ride = getRide(hands.mahjongs, win)
    val toCount: List[CountMahjong] = hands.mahjongs.map(new CountMahjong(_))
    def chows: List[Chow] = {
      val result = getMelds(toCount.filter(!_.isCount).filter(m => m.typ != Types.Word).distinct, isChow, new Chow(_))
      if (result.isEmpty) result else result ::: chows
    }
    def pons = getMelds(toCount.filter(!_.isCount), isPon, new Pon(_))
    def eye = new Eye(toCount.filter(!_.isCount))
    new WinHands(chows, pons, eye, ride, win, hands.isReach, hands.isClosed)
  }
}

