package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong

/**
  * Created by cong on 2016/7/30.
  */
trait PonChecker extends Triples {

  def isPon(toCheck: List[Mahjong]): Boolean = {
    checkTriple(toCheck)
    toCheck(0) == toCheck(2)
  }
}
