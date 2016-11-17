package com.winry.mahjong

import com.winry.mahjong.checker.{ChiChecker, PonChecker}
import com.winry.mahjong.counter.CountMahjong
import com.winry.mahjong.melds._
import com.winry.mahjong.yaku.MyChecker

import scala.collection.mutable.ListBuffer

/**
  * Created by congzhou on 8/1/2016.
  */
class WinHands(hands: Hands, val win: Mahjong) extends ChiChecker with PonChecker {

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

  def getChis: List[Chi] = {
    val result = getMelds(toCount.filter(m => !m.isCount && m.typ != Types.Word).distinct, isChi, new Chi(_))
    if (result.isEmpty) result else result ::: getChis
  }

  hands.push(win)
  //fixme temp variable, how to release this reference?
  private val toCount: List[CountMahjong] = hands.freeMahjongs.map(new CountMahjong(_))

  val chis: List[Chi] = getChis

  val pons: List[Pon] = getMelds(toCount.filter(!_.isCount), isPon, new Pon(_))

  val eye: Eye = {
    val list = toCount.filter(m => !m.isCount && m != win).groupBy(identity).filter(t => t._2.size == 2).values
    if (list.isEmpty) {
      new Eye(List(win, win))
    } else {
      list.head.foreach(_.isCount = true)
      new Eye(list.head)
    }
  }

  val isReach: Boolean = hands.isReach

  val isClosed: Boolean = hands.isClosed

  var yakuCount = new MyChecker(this).check(0)

}
