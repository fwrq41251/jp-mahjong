package com.winry.mahjong.yaku

import com.winry.mahjong.WinHands
import com.winry.mahjong.checker.ConsecutiveChecker

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
    hands.chows.size == 3 && isConsecutive(hands.ride.meld) && hands.eye.isNoValue && hands.ride.isRyanmen
  }
}