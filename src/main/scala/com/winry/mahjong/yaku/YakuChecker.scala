package com.winry.mahjong.yaku

import com.winry.mahjong.WinHands
import com.winry.mahjong.checker.ConsecutiveChecker
import com.winry.mahjong.melds.Chi

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

sealed abstract class CompoundYakuChecker extends YakuChecker {

  override def check(hands: WinHands): Unit = {
    if (satisfy(hands)) hands.add(value)
    next.next.check(hands)
  }
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
    hands.isClosed && hands.chis.size == 4 && hands.eye.isNoValue && hands.ride.isRyanmen
  }
}

/**
  * 一杯口
  */
class IipeikouChecker extends YakuChecker {

  override def value: Int = 1

  override val next: YakuChecker = ???

  override def satisfy(hands: WinHands): Boolean = {
    // convert chi list to set, if there is any duplicate element, set size will be small than list size.
    hands.isClosed && hands.chis.toSet.size != hands.chis.size
  }
}

/**
  * 三色同顺
  *
  * @param isClosed
  */
class SanshokuDoujunChecker(isClosed: Boolean) extends YakuChecker {

  override def value: Int = if (isClosed) 2 else 1

  override val next: YakuChecker = ???

  override def satisfy(hands: WinHands): Boolean = {
    val chis = hands.chis
    if (chis.size >= 3) {
      val map = chis.groupBy(_.typ)
      if (map.size == 3) {
        if (chis.size == 3) {
          chis.head.num == chis(1).num && chis(1).num == chis(2).num
        } else {
          // chis.size == 4
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

  override val next: YakuChecker = ???

  override def satisfy(hands: WinHands): Boolean = {
    val chis = hands.chis
    if (chis.size >= 3) {
      val map = chis.groupBy(_.typ)
      if (map.values.exists(_.size >= 3)) {
        val ikki = map.values.filter(_.size > 3).toList.head
        val typ = ikki.head.typ
        ikki.contains(Chi(typ, 1)) && ikki.contains(Chi(typ, 4)) && ikki.contains(Chi(typ, 7))
      } else false
    } else false
  }
}

/**
  * 两杯口
  */
class RyanpeikouChecker extends CompoundYakuChecker {

  override def value: Int = 3

  override val next: YakuChecker = new IipeikouChecker

  override def satisfy(hands: WinHands): Boolean = {
    val chis = hands.chis
    hands.isClosed && chis.size == 4 && chis.toSet.size == 2
  }
}

/**
  * 对对和
  */
class ToitoihouChecker extends YakuChecker {

  override def value: Int = 2

  override val next: YakuChecker = ???

  override def satisfy(hands: WinHands): Boolean = {
    hands.pons.size == 4
  }
}

/**
  * 三色同刻
  */
class SanshokudoukouChecker extends YakuChecker {

  override def value: Int = ???

  override val next: YakuChecker = ???

  override def satisfy(hands: WinHands): Boolean = {
    val pons = hands.pons
    if (pons.size >= 3) {
      val map = pons.groupBy(_.typ)
      if (map.values.exists(_.size >= 3)) {
        if (pons.size == 3) {
          pons.head.num == pons(1).num && pons(1).num == pons(2).num
        } else {
          // pons.size == 4
          val single = map.values.filter(_.size == 1).toList
          if (single.head == single(1)) map.values.filter(_.size > 1).toList.contains(single.head) else false
        }
      } else false
    } else false
  }
}

/**
  * 断幺九
  */
class TanyaochuuChecker extends YakuChecker {

  override def value: Int = 1

  override val next: YakuChecker = ???

  override def satisfy(hands: WinHands): Boolean = {
    hands.chis.forall(!_.isTaiYao) && hands.pons.forall(!_.isTaiYao) && !hands.eye.isTaiYao
  }
}

class YakuhaiChecker extends YakuChecker {

  override def value: Int = 1

  override val next: YakuChecker = ???

  override def satisfy(hands: WinHands): Boolean = ???
}

/**
  * 三暗刻
  */
class SanankoChecker extends YakuChecker {

  override def value: Int = 2

  override val next: YakuChecker = _

  override def satisfy(hands: WinHands): Boolean = {
    hands.pons.count(_.isClosed) >= 3
  }
}