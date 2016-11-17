package com.winry.mahjong.melds

import com.winry.mahjong.Types.Type
import com.winry.mahjong.checker.TriplesChecker
import com.winry.mahjong.{Mahjong, Types}

/**
  * Created by congzhou on 8/1/2016.
  */
class Pon(mahjongs: List[Mahjong]) extends Triple with TriplesChecker with Closed {

  override val num: Int = mahjongs.head.num
  override val typ: Type = mahjongs.head.typ

  def this(chi: List[Mahjong], closed: Boolean) = {
    this(chi)
    isClosed = closed
  }

  checkTriple(mahjongs)

  /**
    * 是否纯全
    *
    * @return
    */
  def isJunTaiYao: Boolean = {
    !typ.isWord && (num == 1 || num == 9)
  }

  /**
    * 是否带幺九
    *
    * @return
    */
  def isTaiYao: Boolean = {
    isJunTaiYao || typ == Types.Word
  }


  def canEqual(other: Any): Boolean = other.isInstanceOf[Pon]

  override def equals(other: Any): Boolean = other match {
    case that: Pon =>
      (that canEqual this) &&
        num == that.num &&
        typ == that.typ
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(num, typ)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}
