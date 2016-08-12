package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong

/**
  * Created by cong on 2016/7/30.
  */
trait ChiChecker extends TriplesChecker with SkipChecker {

  def isChi(toCheck: List[Mahjong]): Boolean = {
    checkTriple(toCheck)
    isSkip(toCheck)
  }

}
