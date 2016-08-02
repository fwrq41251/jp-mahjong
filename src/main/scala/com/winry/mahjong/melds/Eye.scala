package com.winry.mahjong.melds

import com.winry.mahjong.checker.Pairs
import com.winry.mahjong.{Mahjong, Types}

/**
  * Created by congzhou on 8/1/2016.
  */
class Eye(val eye: List[Mahjong]) extends Pairs {

  checkPair(eye)

  /**
    * 是否客风
    * @return
    */
  def isNoValue: Boolean = true

  /**
    * 是否纯全
    * @return
    */
  def isJunTaiYao: Boolean = {
    eye.head.num == 1 || eye.head.num == 9
  }

  /**
    * 是否带幺九
    * @return
    */
  def isTaiYao: Boolean = {
    isJunTaiYao || eye.head.typ == Types.Word
  }
}
