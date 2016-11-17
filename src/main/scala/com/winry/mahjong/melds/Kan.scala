package com.winry.mahjong.melds

import com.winry.mahjong.Types.Type
import com.winry.mahjong.{Mahjong, Types}

/**
  * Created by congzhou on 8/12/2016.
  */
class Kan(mahjons: List[Mahjong]) extends Meld {

  override val num: Int = mahjons.head.num
  override val typ: Type = mahjons.head.typ

  if (mahjons.size != 4) throw new IllegalArgumentException("must be a four!")

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

}
