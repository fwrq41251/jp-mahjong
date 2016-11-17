package com.winry.mahjong

import com.winry.mahjong.checker.{ChiChecker, PonChecker, RideChecker}
import com.winry.mahjong.counter.CountMahjong
import com.winry.mahjong.melds._

import scala.collection.mutable.ListBuffer

/**
  * Created by congzhou on 8/1/2016.
  */
class WinHands(val chis: List[Chi], val pons: List[Pon], val eye: Eye, val win: Mahjong, val isReach: Boolean, val
isClosed: Boolean) extends ChiChecker with PonChecker {

  var yakuCount = 0

  def add(value: Int): Unit = {
    yakuCount += value
  }

}

object WinHands extends ChiChecker with PonChecker with RideChecker {

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
    hands.push(win)
    val toCount: List[CountMahjong] = hands.freeMahjongs.map(new CountMahjong(_))
    def getChis: List[Chi] = {
      val result = getMelds(toCount.filter(m => !m.isCount && m.typ != Types.Word).distinct, isChi, new Chi(_))
      if (result.isEmpty) result else result ::: getChis
    }
    val chis = getChis
    val pons = getMelds(toCount.filter(!_.isCount), isPon, new Pon(_))
    val eye: Eye = {
      val list = toCount.filter(m => !m.isCount && m != win).groupBy(identity).filter(t => t._2.size == 2).values
      if (list.isEmpty) {
        new Eye(List(win, win))
      } else {
        list.head.foreach(_.isCount = true)
        new Eye(list.head)
      }
    }
    new WinHands(chis ++ hands.chis, pons ++ hands.pons, eye, win, hands.isReach, hands.isClosed)
  }
}

