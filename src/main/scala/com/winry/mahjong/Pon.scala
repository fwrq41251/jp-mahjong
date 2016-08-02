package com.winry.mahjong

import com.winry.mahjong.checker.Triples

/**
  * Created by congzhou on 8/1/2016.
  */
class Pon(val pon: List[Mahjong]) extends Triples {

  checkTriple(pon)

  /**
    * 是否纯全
    * @return
    */
  def isJunTaiYao: Boolean = {
    pon.head.num == 1 || pon.head.num == 9
  }

  /**
    * 是否带幺九
    * @return
    */
  def isTaiYao: Boolean = {
    isJunTaiYao || pon.head.typ == Types.Word
  }

  override def equals(that: scala.Any): Boolean = that match {
    case that: Pon => this.pon.head == that.pon.head
    case _ => false
  }
}

object Pon {

  def apply(pon: List[Mahjong]): Pon = new Pon(pon)
}
