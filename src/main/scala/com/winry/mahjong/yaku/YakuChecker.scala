package com.winry.mahjong.yaku

/**
  * Created by congzhou on 8/1/2016.
  */
sealed abstract class YakuChecker(val next: YakuChecker) {

  def check(hands: WinHands): Unit
}

class ReachChecker(override val next: YakuChecker) extends YakuChecker(next) {

  override def check(hands: WinHands): Unit = {
    if (hands.isReach) hands.increaseBy(1)
    next.check(hands)
  }
}

class PinfuChecker(override val next: YakuChecker) extends YakuChecker(next) {

  override def check(hands: WinHands): Unit = {
    if (hands.chows.size == 4 && hands.eyes(0).isNoValue) hands.increaseBy(1)
    next.check(hands)
  }
}