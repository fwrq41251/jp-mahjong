package com.winry.mahjong.melds

import com.winry.mahjong.Types.Type
import com.winry.mahjong.checker.PairsChecker
import com.winry.mahjong.{Mahjong, Types}

/**
  * Created by congzhou on 8/1/2016.
  */
class Eye(mahjongs: List[Mahjong]) extends Meld with PairsChecker {

  checkPair(mahjongs)

  override val num: Int = mahjongs.head.num
  override val typ: Type = mahjongs.head.typ

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
