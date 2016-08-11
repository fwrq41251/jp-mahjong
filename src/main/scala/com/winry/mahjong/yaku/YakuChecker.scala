package com.winry.mahjong.yaku

import com.winry.mahjong.WinHands
import com.winry.mahjong.checker.ConsecutiveChecker
import com.winry.mahjong.melds.Chow

import scala.collection.mutable

/**
  * Created by congzhou on 8/1/2016.
  */
sealed abstract class YakuChecker extends ConsecutiveChecker {

  def value: Int

  val next: YakuChecker

  def check(hands: WinHands): Unit = {
    if (apply(hands)) hands.add(value)
    next.check(hands)
  }

  def apply(hands: WinHands): Boolean
}

/**
  * 立直
  */
class ReachChecker extends YakuChecker {

  override val next: YakuChecker = ???

  override def value: Int = 1

  override def apply(hands: WinHands): Boolean = {
    hands.isReach
  }
}

/**
  * 平和
  */
class PinfuChecker extends YakuChecker {

  override val next: YakuChecker = ???

  override def value: Int = 1

  override def apply(hands: WinHands): Boolean = {
    hands.chows.size == 4 && hands.eye.isNoValue && hands.ride.isRyanmen
  }
}

/**
  * 一杯口
  */
class IipeikouChecker extends YakuChecker {

  override def value: Int = 1

  override val next: YakuChecker = _

  override def apply(hands: WinHands): Boolean = {
    // convert chow list to set, if there is any duplicate element, set size will be small than list size.
    hands.chows.toSet.size != hands.chows.size
  }
}

/**
  * 三色同顺
  *
  * @param isClosed
  */
class SanshokuDoujunCheckr(isClosed: Boolean) extends YakuChecker {

  override def value: Int = if (isClosed) 2 else 1

  override val next: YakuChecker = _

  override def apply(hands: WinHands): Boolean = {
    val temp = hands.chows
    if (temp.size > 2) {
      val map = temp.groupBy(c => c.typ)
      if (map.size == 3) {
        if (temp.size == 3) {
          temp(0).num == temp(1).num && temp(1).num == temp(2).num
        } else {
          // temp.zie == 4

        }
      } else {
        false
      }
    } else false
  }
}