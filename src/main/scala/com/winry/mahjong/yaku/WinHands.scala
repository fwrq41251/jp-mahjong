package com.winry.mahjong.yaku

import com.winry.mahjong.checker.{ChowChecker, PonChecker}
import com.winry.mahjong.counter.CountMahjong
import com.winry.mahjong.{Eye, Mahjong, _}

import scala.collection.mutable.ListBuffer

/**
  * Created by congzhou on 8/1/2016.
  */
class WinHands(val chows: List[Chow], val pons: List[Pon], val eye: Eye, val isReach: Boolean) extends ChowChecker with PonChecker {

  var yakuCount = 0

  def add(value: Int): Unit = {
    yakuCount += value
  }

}

object WinHands extends ChowChecker with PonChecker {

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

  def apply(hands: Hands): WinHands = {
    val toCount: List[CountMahjong] = hands.mahjongs.map(new CountMahjong(_))
    def chows:List[Chow] = {
      val result = getMelds(toCount.filter(!_.isCount).filter(m => m.typ != Types.Word).distinct, isChow, Chow.apply)
      if (result.size == 0) result else result ::: chows
    }
    def pons = getMelds(toCount.filter(!_.isCount), isPon, Pon.apply)
    def eye = new Eye(toCount.filter(!_.isCount))
    new WinHands(chows, pons, eye, hands.isReach)
  }
}


