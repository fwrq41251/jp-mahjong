package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong

/**
  * Created by cong on 2016/7/30.
  */
trait ConsecutiveChecker {

  def isConsecutive(one: Mahjong, two: Mahjong): Boolean = one.typ == two.typ && one.num + 1 == two.num
}
