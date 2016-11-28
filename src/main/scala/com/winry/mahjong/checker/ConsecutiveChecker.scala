package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong

/**
  * Created by cong on 2016/7/30.
  */
trait ConsecutiveChecker {

  def isConsecutive(toCheck: List[Mahjong]): Boolean = toCheck.head.typ == toCheck(1).typ && toCheck.head.num + 1 == toCheck(1).num
}
