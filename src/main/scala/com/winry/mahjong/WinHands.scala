package com.winry.mahjong

import com.winry.mahjong.checker.{ChiChecker, PonChecker}
import com.winry.mahjong.counter.CountMahjong
import com.winry.mahjong.melds._

import scala.collection.mutable.ListBuffer

/**
  * Created by congzhou on 8/1/2016.
  */
class WinHands(hands: Hands, val win: Mahjong) extends ChiChecker with PonChecker {

  def getMelds[A](toCount: List[CountMahjong], predicate: List[Mahjong] => Boolean, apply: List[Mahjong] => A):
  List[A] = {
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
  private val toCount: List[CountMahjong] = hands.freeMahjongs.toList.map(new CountMahjong(_))

  val eyes: List[Eye] = {
    if (toCount.size == 14) {
      val groups = toCount.groupBy(identity)
      if (groups.size == 7) {
        toCount.foreach(_.isCount = true)
        groups.values.map(v => new Eye(v.toList)).toList
      } else Nil
    } else Nil
  }

  val chis: List[Chi] = hands.chis.toList ::: getChis

  val pons: List[Pon] = hands.pons.toList ::: getMelds(toCount.filter(!_.isCount), isPon, new Pon(_))

  val kans: List[Kan] = hands.kans.toList

  val eye: Eye = {
    val list = toCount.filter(m => !m.isCount && m != win).groupBy(identity).filter(t => t._2.size == 2).values
    if (list.isEmpty) {
      new Eye(win)
    } else {
      list.head.foreach(_.isCount = true)
      new Eye(list.head)
    }
  }

  val isReach: Boolean = hands.isReach

  val isClosed: Boolean = hands.isClosed

  /**
    * 是否两面待
    *
    * @return
    */
  def isRyanmen: Boolean = {
    win.num match {
      case it if 1 to 3 contains it => hands.chis.contains(new Chi(win))
      case it if 4 to 6 contains it => hands.chis.contains(new Chi(win)) || hands.chis.contains(new Chi(win
        .typ, it - 2))
      case _ => hands.chis.contains(new Chi(win.typ, win.num - 2))
    }
  }

  /**
    * 是否单骑
    *
    * @return
    */
  def isTanki: Boolean = {
    eye == new Eye(win)
  }

  /**
    * 是否七对子
    *
    * @return
    */
  def isChītoitsu: Boolean = {
    eyes.nonEmpty
  }

}
