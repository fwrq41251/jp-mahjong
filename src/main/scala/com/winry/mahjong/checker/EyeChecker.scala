package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong

/**
  * Created by cong on 2016/7/30.
  */
trait EyeChecker extends PairChecker {

  def isEye(toCheck:List[Mahjong]): Boolean = {
    checkPair(toCheck)
    toCheck(0) == toCheck(1)
  }
}
