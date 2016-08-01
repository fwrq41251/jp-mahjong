package com.winry.mahjong

import com.winry.mahjong.Types.Type

/**
  * Created by congzhou on 7/29/2016.
  */
class Mahjong(val typ: Type, val num: Int) extends Ordered[Mahjong] {

  def this(mahjong: Mahjong) = this(mahjong.typ, mahjong.num)

  def canEqual(other: Any): Boolean = other.isInstanceOf[Mahjong]

  override def equals(other: Any): Boolean = other match {
    case that: Mahjong =>
      (that canEqual this) &&
        typ == that.typ &&
        num == that.num
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(typ, num)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }


  override def toString = s"Mahjong($typ, $num)"

  override def compare(that: Mahjong): Int = {
    if (this.typ == that.typ) this.num.compareTo(that.num)
    else this.typ.compareTo(that.typ)
  }
}
