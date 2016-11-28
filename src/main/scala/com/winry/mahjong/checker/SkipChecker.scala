package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong

/**
  * Created by congzhou on 8/2/2016.
  */
trait SkipChecker {

  def isSkip(toCheck: List[Mahjong]): Boolean = toCheck.head.typ == toCheck(1).typ && toCheck.head.num + 2 == toCheck(1).num
}
