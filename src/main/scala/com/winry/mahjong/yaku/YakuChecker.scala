package com.winry.mahjong.yaku

import com.winry.mahjong.WinHands
import com.winry.mahjong.checker.ConsecutiveChecker
import com.winry.mahjong.melds.Chow

/**
  * Created by congzhou on 8/1/2016.
  */
sealed abstract class YakuChecker extends ConsecutiveChecker {

  def value: Int

  val next: YakuChecker

  def check(hands: WinHands): Unit = {
    if (satisfy(hands)) hands.add(value)
    next.check(hands)
  }

  def satisfy(hands: WinHands): Boolean
}

/**
  * 立直
  */
class ReachChecker extends YakuChecker {

  override val next: YakuChecker = ???

  override def value: Int = 1

  override def satisfy(hands: WinHands): Boolean = {
    hands.isReach
  }
}

/**
  * 平和
  */
class PinfuChecker extends YakuChecker {

  override val next: YakuChecker = ???

  override def value: Int = 1

  override def satisfy(hands: WinHands): Boolean = {
    hands.chows.size == 4 && hands.eye.isNoValue && hands.ride.isRyanmen
  }
}

/**
  * 一杯口
  */
class IipeikouChecker extends YakuChecker {

  override def value: Int = 1

  override val next: YakuChecker = _

  override def satisfy(hands: WinHands): Boolean = {
    // convert chow list to set, if there is any duplicate element, set size will be small than list size.
    hands.chows.toSet.size != hands.chows.size
  }
}

/**
  * 三色同顺
  *
  * @param isClosed
  */
class SanshokuDoujunChecker(isClosed: Boolean) extends YakuChecker {

  override def value: Int = if (isClosed) 2 else 1

  override val next: YakuChecker = _

  override def satisfy(hands: WinHands): Boolean = {
    val chows = hands.chows
    if (chows.size >= 3) {
      val map = chows.groupBy(_.typ)
      if (map.size == 3) {
        if (chows.size == 3) {
          chows.head.num == chows(1).num && chows(1).num == chows(2).num
        } else {
          // chows.zie == 4
          val single = map.values.filter(_.size == 1).toList
          if (single.head == single(1)) map.values.filter(_.size > 1).toList.contains(single.head) else false
        }
      } else false
    } else false
  }

}

/**
  * 一气通贯
  *
  * @param isClosed
  */
class IkkitsuukanChecker(isClosed: Boolean) extends YakuChecker {

  override def value: Int = if (isClosed) 2 else 1

  override val next: YakuChecker = _

  override def satisfy(hands: WinHands): Boolean = {
    val chows = hands.chows
    if (chows.size >= 3) {
      val map = chows.groupBy(_.typ)
      if (map.values.exists(_.size >= 3)) {
        val ikki = map.values.filter(_.size > 3).toList
        val typ = ikki.head.head.typ
        ikki.contains(Chow(typ, 1)) && ikki.contains(Chow(typ, 4)) && ikki.contains(Chow(typ, 7))
      } else false
    } else false
  }
}

/**
  * 两杯口
  */
class RyanpeikouChecker extends YakuChecker {

  override def value: Int = 3

  override val next: YakuChecker = _

  override def satisfy(hands: WinHands): Boolean = {
    val chows = hands.chows
    chows.size == 4 && chows.toSet.size == 2
  }
}

/**
  * 对对和
  */
class ToitoihouChecker extends YakuChecker {

  override def value: Int = 2

  override val next: YakuChecker = _

  override def satisfy(hands: WinHands): Boolean = {
    hands.pons.size == 4
  }
}