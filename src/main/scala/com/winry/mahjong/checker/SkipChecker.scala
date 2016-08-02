package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong

/**
  * Created by congzhou on 8/2/2016.
  */
trait SkipChecker {

  def isSkip(one: Mahjong, two: Mahjong): Boolean = one.typ == two.typ && one.num + 2 == two.num
}
