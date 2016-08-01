package com.winry.mahjong

import com.winry.mahjong.checker.PairChecker

/**
  * Created by congzhou on 8/1/2016.
  */
class Eye(val eye: List[Mahjong]) extends PairChecker {

  checkPair(eye)

  /**
    * 是否客风
    * @return
    */
  def isNoValue: Boolean = true
}
