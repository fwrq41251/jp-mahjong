package com.winry.mahjong.melds

import com.winry.mahjong.Mahjong
import com.winry.mahjong.Types.Type

/**
  * Created by congzhou on 8/1/2016.
  */
class Chi(mahjongs: List[Mahjong]) extends Triple with Closed {

  val head: Mahjong = mahjongs.sorted.head

  override val typ: Type = head.typ

  override val num: Int = head.num

  def this(mahjongs: List[Mahjong], closed: Boolean) = {
    this(mahjongs)
    isClosed = closed
  }

  def this(typ: Type, num: Int) = {
    this(List(new Mahjong(typ, num)))
  }

  /**
    * 是否带幺九
    *
    * @return
    */
  def isTaiYao: Boolean = {
    num == 1 || num == 7
  }


  def canEqual(other: Any): Boolean = other.isInstanceOf[Chi]

  override def equals(other: Any): Boolean = other match {
    case that: Chi =>
      (that canEqual this) &&
        typ == that.typ &&
        num == that.num
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(typ, num)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }
}

