package com.winry.mahjong

import com.winry.mahjong.checker.TripleChecker

/**
  * Created by congzhou on 8/1/2016.
  */
class Chow(val chow: List[Mahjong]) extends TripleChecker {

  checkTriple(chow)

  /**
    * 是否带幺九
    *
    * @return
    */
  def isTaiYao(): Boolean = {
    chow.head.num == 1 || chow.head.num == 7
  }

  override def equals(that: scala.Any): Boolean = that match {
    case that: Chow => this.chow.head == that.chow.head
    case _ => false
  }
}