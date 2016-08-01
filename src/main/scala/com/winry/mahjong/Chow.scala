package com.winry.mahjong

import com.winry.mahjong.checker.TripleChecker

/**
  * Created by congzhou on 8/1/2016.
  */
class Chow(val chow: List[Mahjong]) extends TripleChecker {

  checkTriple(chow)

  def isTaiYao(): Boolean = {
    chow(0).num == 1 || chow(0).num == 7
  }
}
