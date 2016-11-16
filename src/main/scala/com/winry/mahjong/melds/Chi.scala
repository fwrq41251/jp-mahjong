package com.winry.mahjong.melds

import com.winry.mahjong.Mahjong
import com.winry.mahjong.Types.Type
import com.winry.mahjong.checker.TriplesChecker

/**
  * Created by congzhou on 8/1/2016.
  */
class Chi(chi: List[Mahjong]) extends Triple with TriplesChecker with Closed {

  def this(chi: List[Mahjong], closed: Boolean) = {
    this(chi)
    isClosed = closed
  }

  checkTriple(chi)

  /**
    * 是否带幺九
    *
    * @return
    */
  def isTaiYao: Boolean = {
    meld.head.num == 1 || meld.head.num == 7
  }

  override def equals(that: scala.Any): Boolean = that match {
    case that: Chi => this.meld.head == that.meld.head
    case _ => false
  }

  override val meld: List[Mahjong] = chi
}

object Chi {

  def apply(typ: Type, num: Int): Chi = {
    new Chi(List(new Mahjong(typ, num)))
  }
}
