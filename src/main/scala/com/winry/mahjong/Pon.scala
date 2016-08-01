package com.winry.mahjong

import com.winry.mahjong.checker.TripleChecker

/**
  * Created by congzhou on 8/1/2016.
  */
class Pon(val pon: List[Mahjong]) extends TripleChecker {

  checkTriple(pon)

  def isJunTaiYao: Boolean = {
    pon.head.num == 1 || pon.head.num == 9
  }

  def isTaiYao: Boolean = {
    isJunTaiYao || pon.head.typ == Types.Word
  }
}
