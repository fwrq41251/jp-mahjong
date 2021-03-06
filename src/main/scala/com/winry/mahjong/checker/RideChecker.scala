package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong

/**
  * Created by cong on 2016/7/30.
  */
trait RideChecker extends PairsChecker with ConsecutiveChecker with EyeChecker with SkipChecker {

  def isRide(toCheck:List[Mahjong]): Boolean = {
    checkPair(toCheck)
    isEye(toCheck) || isConsecutive(toCheck) || isSkip(toCheck)
  }

}
