package com.winry.mahjong.melds

import com.winry.mahjong.Mahjong
import com.winry.mahjong.Types.Type

/**
  * Created by congzhou on 8/2/2016.
  */
trait Meld {

  val meld: List[Mahjong]

  def typ: Type = meld.head.typ
}
