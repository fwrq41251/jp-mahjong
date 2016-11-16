package com.winry.mahjong.yaku

import com.winry.mahjong.{Mahjong, WinHands}
import com.winry.mahjong.checker.{ChiChecker, ConsecutiveChecker, PonChecker}
import com.winry.mahjong.melds.{Chi, Pon, Ride}

/**
  * Created by congzhou on 8/1/2016.
  */
sealed abstract class YakuChecker(hands: WinHands) extends ConsecutiveChecker {

  def value: Int

  val next: YakuChecker

  def check(): Unit = {
    if (satisfy()) hands.add(value)
    next.check()
  }

  def satisfy(): Boolean
}

sealed abstract class CompoundYakuChecker(hands: WinHands) extends YakuChecker(hands) {

  override def check(): Unit = {
    if (satisfy()) hands.add(value)
    next.next.check()
  }
}

class MyChecker(hands: WinHands) extends YakuChecker(hands) {

  override def value: Int = 0

  override val next: YakuChecker = new ReachChecker(hands)

  override def satisfy(): Boolean = false
}

/**
  * 立直
  */
class ReachChecker(hands: WinHands) extends YakuChecker(hands) {

  override val next: YakuChecker = ???

  override def value: Int = 1

  override def satisfy(): Boolean = {
    hands.isReach
  }
}

/**
  * 平和
  */
class PinfuChecker(hands: WinHands) extends YakuChecker(hands) {

  override val next: YakuChecker = ???

  override def value: Int = 1

  override def satisfy(): Boolean = {
    hands.isClosed && hands.chis.size == 4 && hands.eye.isNoValue && hands.ride.isRyanmen
  }
}

/**
  * 一杯口
  */
class IipeikouChecker(hands: WinHands) extends YakuChecker(hands) {

  override def value: Int = 1

  override val next: YakuChecker = ???

  override def satisfy(): Boolean = {
    // convert chi list to set, if there is any duplicate element, set size will be small than list size.
    hands.isClosed && hands.chis.toSet.size != hands.chis.size
  }
}

/**
  * 三色同顺
  *
  */
class SanshokuDoujunChecker(hands: WinHands) extends YakuChecker(hands) {

  override def value: Int = if (hands.isClosed) 2 else 1

  override val next: YakuChecker = ???

  override def satisfy(): Boolean = {
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
  */
class IkkitsuukanChecker(hands: WinHands) extends YakuChecker(hands) {

  override def value: Int = if (hands.isClosed) 2 else 1

  override val next: YakuChecker = ???

  override def satisfy(): Boolean = {
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
class RyanpeikouChecker(hands: WinHands) extends CompoundYakuChecker(hands) {

  override def value: Int = 3

  override val next: YakuChecker = new IipeikouChecker(hands)

  override def satisfy(): Boolean = {
    val chis = hands.chis
    hands.isClosed && chis.size == 4 && chis.toSet.size == 2
  }
}

/**
  * 对对和
  */
class ToitoihouChecker(hands: WinHands) extends YakuChecker(hands) {

  override def value: Int = 2

  override val next: YakuChecker = ???

  override def satisfy(): Boolean = {
    hands.pons.size == 4
  }
}

/**
  * 三色同刻
  */
class SanshokudoukouChecker(hands: WinHands) extends YakuChecker(hands) {

  override def value: Int = ???

  override val next: YakuChecker = ???

  override def satisfy(): Boolean = {
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
class TanyaochuuChecker(hands: WinHands) extends YakuChecker(hands) {

  override def value: Int = 1

  override val next: YakuChecker = ???

  override def satisfy(): Boolean = {
    hands.chis.forall(!_.isTaiYao) && hands.pons.forall(!_.isTaiYao) && !hands.eye.isTaiYao
  }
}

class YakuhaiChecker(hands: WinHands) extends YakuChecker(hands) {

  override def value: Int = 1

  override val next: YakuChecker = ???

  override def satisfy(): Boolean = ???
}

/**
  * 三暗刻
  */
class SanankoChecker(hands: WinHands) extends YakuChecker(hands) {

  override def value: Int = 2

  override val next: YakuChecker = ???

  override def satisfy(): Boolean = {
    hands.pons.count(_.isClosed) >= 3
  }
}

/**
  * 全带
  *
  */
class ChantaiyaoChecker(hands: WinHands) extends YakuChecker(hands) with ChiChecker with PonChecker {

  override def value: Int = if (hands.isClosed) 2 else 1

  override val next: YakuChecker = ???

  override def satisfy(): Boolean = {
    def isTaiYao(ride: Ride, win: Mahjong): Boolean = {
      val meld = ride.meld
      if (meld.size == 1) {
        meld.head.typ.isWord || meld.head.num == 1 || meld.head.num == 9
      } else {
        val list = meld ::: List(win)
        if (isChi(list)) {
          new Chi(list).isTaiYao
        } else {
          new Pon(list).isTaiYao
        }
      }
    }
    hands.chis.forall(_.isTaiYao) && hands.pons.forall(_.isTaiYao) && hands.eye.isTaiYao && isTaiYao(hands.ride,
      hands.win)
  }
}