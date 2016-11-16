package com.winry.mahjong.melds

import com.winry.mahjong.checker.TriplesChecker
import com.winry.mahjong.{Mahjong, Types}

/**
  * Created by congzhou on 8/1/2016.
  */
class Pon(pon: List[Mahjong]) extends Triple with TriplesChecker with Closed {

  def this(chi: List[Mahjong], closed: Boolean) = {
    this(chi)
    isClosed = closed
  }

  checkTriple(pon)

  /**
    * 是否纯全
    *
    * @return
    */
  def isJunTaiYao: Boolean = {
    !meld.head.typ.isWord && (meld.head.num == 1 || meld.head.num == 9)
  }

  /**
    * 是否带幺九
    *
    * @return
    */
  def isTaiYao: Boolean = {
    isJunTaiYao || meld.head.typ == Types.Word
  }

  override def equals(that: scala.Any): Boolean = that match {
    case that: Pon => this.meld.head == that.meld.head
    case _ => false
  }

  override val meld: List[Mahjong] = pon
}
