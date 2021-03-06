package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong

/**
  * Created by cong on 2016/7/31.
  */
trait TriplesChecker {

  def checkTriple(toCheck: List[Mahjong]): Unit = {
    if (toCheck.size != 3) throw new IllegalArgumentException("must be a triple!")
  }
}
