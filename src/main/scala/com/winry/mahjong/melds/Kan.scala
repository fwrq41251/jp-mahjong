package com.winry.mahjong.melds

import com.winry.mahjong.Types.Type
import com.winry.mahjong.{Mahjong, Types}

/**
  * Created by congzhou on 8/12/2016.
  */
class Kan(mahjons: List[Mahjong]) extends Meld with Closed {

  override val num: Int = mahjons.head.num
  override val typ: Type = mahjons.head.typ

  def this(mahjons: List[Mahjong], isClosed: Boolean) = {
    this(mahjons)
    this.isClosed = isClosed
  }

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

  def toPon: Pon = {
    new Pon(List(Mahjong(typ, num)), isClosed)
  }

}
