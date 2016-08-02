package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong

/**
  * Created by cong on 2016/7/30.
  */
trait ChowChecker extends Triples with SkipChecker {

  def isChow(toCheck: List[Mahjong]): Boolean = {
    checkTriple(toCheck)
    isSkip(toCheck(0), toCheck(2))
  }

}
