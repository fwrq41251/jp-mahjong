package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong

/**
  * Created by cong on 2016/7/30.
  */
trait PairChecker {

  def checkPair(toCheck: List[Mahjong]): Unit = {
    if (toCheck.size != 2) throw new IllegalArgumentException("chow must be a pair!")
  }
}
