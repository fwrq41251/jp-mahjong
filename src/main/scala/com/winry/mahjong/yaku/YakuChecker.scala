package com.winry.mahjong.yaku

/**
  * Created by congzhou on 8/1/2016.
  */
sealed abstract class YakuChecker {

  val next: YakuChecker
  def check(hands: WinHands): Unit
}

/**
  * 立直
  */
class ReachChecker extends YakuChecker {

  override def check(hands: WinHands): Unit = {
    if (hands.isReach) hands.add(1)
    next.check(hands)
  }

  override val next: YakuChecker = ???
}

/**
  * 平和
  */
class PinfuChecker extends YakuChecker {

  override def check(hands: WinHands): Unit = {
    if (hands.chows.size == 4 && hands.eye.isNoValue) hands.add(1)
    next.check(hands)
  }

  override val next: YakuChecker = ???
}