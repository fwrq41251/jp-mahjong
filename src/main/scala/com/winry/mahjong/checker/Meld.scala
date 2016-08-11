package com.winry.mahjong.checker

import com.winry.mahjong.Mahjong
import com.winry.mahjong.Types.Type

/**
  * Created by congzhou on 8/2/2016.
  */
trait Meld {

  val meld: List[Mahjong]

  def num: Int = meld.head.num

  def typ: Type = meld.head.typ
}
