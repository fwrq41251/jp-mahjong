package com.winry.mahjong.melds

import com.winry.mahjong.checker.Pairs
import com.winry.mahjong.{Mahjong, Types}

/**
  * Created by congzhou on 8/1/2016.
  */
class Eye(eye: List[Mahjong]) extends Pairs {

  checkPair(eye)

  /**
    * 是否客风
    *
    * @return
    */
  def isNoValue: Boolean = true

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

  override val meld: List[Mahjong] = eye
}
