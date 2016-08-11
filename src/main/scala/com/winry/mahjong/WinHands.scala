package com.winry.mahjong

import com.winry.mahjong.checker.{ChowChecker, PonChecker, RideChecker}
import com.winry.mahjong.counter.CountMahjong
import com.winry.mahjong.melds.{Chow, Eye, Pon, Ride}

import scala.collection.mutable.ListBuffer

/**
  * Created by congzhou on 8/1/2016.
  */
class WinHands(val chows: List[Chow], val pons: List[Pon], val eye: Eye, val ride: Ride, val win: Mahjong, val isReach: Boolean) extends ChowChecker with PonChecker {

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

  def apply(hands: Hands, win: Mahjong): WinHands = {
    val toCount: List[CountMahjong] = hands.mahjongs.map(new CountMahjong(_))
    def chows: List[Chow] = {
      val result = getMelds(toCount.filter(!_.isCount).filter(m => m.typ != Types.Word).distinct, isChow, new Chow(_))
      if (result.isEmpty) result else result ::: chows
    }
    def pons = getMelds(toCount.filter(!_.isCount), isPon, new Pon(_))
    def ride = getMelds(toCount.filter(!_.isCount), isRide, new Ride(_))
    def eye = new Eye(toCount.filter(!_.isCount))
    new WinHands(chows, pons, eye, ride.head, win, hands.isReach)
  }
}

