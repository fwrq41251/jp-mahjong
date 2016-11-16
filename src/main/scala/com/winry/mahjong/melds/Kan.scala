package com.winry.mahjong.melds

import com.winry.mahjong.{Mahjong, Types}

/**
  * Created by congzhou on 8/12/2016.
  */
class Kan(kan: List[Mahjong]) extends Meld {

  if (kan.size != 4) throw new IllegalArgumentException("must be a four!")

  override val meld: List[Mahjong] = kan

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
}
