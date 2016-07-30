package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong

/**
  * Created by cong on 2016/7/30.
  */
trait ChowChecker extends TripleChecker with ConsecutiveChecker {

  def isChow(toCheck: List[Mahjong]): Boolean = {
    checkTriple(toCheck)
    isConsecutive(toCheck(0), toCheck(1)) && isConsecutive(toCheck(1), toCheck(2))
  }

}
