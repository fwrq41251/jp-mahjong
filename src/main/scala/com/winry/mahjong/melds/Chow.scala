package com.winry.mahjong.melds

import com.winry.mahjong.Mahjong
import com.winry.mahjong.checker.Triples

/**
  * Created by congzhou on 8/1/2016.
  */
class Chow(chow: List[Mahjong]) extends Triples {

  checkTriple(chow)

  /**
    * 是否带幺九
    *
    * @return
    */
  def isTaiYao: Boolean = {
    meld.head.num == 1 || meld.head.num == 7
  }

  override def equals(that: scala.Any): Boolean = that match {
    case that: Chow => this.meld.head == that.meld.head
    case _ => false
  }

  override val meld: List[Mahjong] = chow
}

object Chow {

  def apply(chow: List[Mahjong]): Chow = new Chow(chow)
}
