package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong

/**
  * Created by cong on 2016/7/30.
  */
trait RideChecker extends PairChecker with ConsecutiveChecker with EyeChecker {

  def isRide(toCheck:List[Mahjong]): Boolean = {
    checkPair(toCheck)
    def isStep(one: Mahjong, two: Mahjong): Boolean = one.typ == two.typ && one.num + 2 == two.num
    isEye(toCheck) || isConsecutive(toCheck(0), toCheck(1)) || isStep(toCheck(0), toCheck(1))
  }

}
