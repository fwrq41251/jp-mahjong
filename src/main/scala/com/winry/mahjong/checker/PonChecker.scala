package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong

/**
  * Created by cong on 2016/7/30.
  */
trait PonChecker extends TriplesChecker {

  def isPon(toCheck: List[Mahjong]): Boolean = {
    checkTriple(toCheck)
    toCheck.head == toCheck(2)
  }
}
