package com.winry.mahjong

import com.winry.mahjong.checker.PairChecker

/**
  * Created by congzhou on 8/1/2016.
  */
class Eye(val pon: List[Mahjong]) extends PairChecker {

  checkPair(pon)

  def isNoValue: Boolean = true
}
