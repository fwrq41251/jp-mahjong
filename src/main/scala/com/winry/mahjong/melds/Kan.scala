package com.winry.mahjong.melds

import com.winry.mahjong.Mahjong

/**
  * Created by congzhou on 8/12/2016.
  */
class Kan(kan: List[Mahjong]) extends Meld {

  if (kan.size != 4) throw new IllegalArgumentException("must be a four!")

  override val meld: List[Mahjong] = kan
}
