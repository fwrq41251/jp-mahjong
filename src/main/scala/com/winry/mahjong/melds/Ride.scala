package com.winry.mahjong.melds

import com.winry.mahjong.Mahjong
import com.winry.mahjong.checker.{ConsecutiveChecker, Meld, Pairs}

/**
  * Created by congzhou on 8/2/2016.
  */
class Ride(ride: List[Mahjong]) extends Meld with Pairs with ConsecutiveChecker {

  checkPair(ride)

  /**
    * 是否两面待
    *
    * @return
    */
  def isRyanmen: Boolean = {
    isConsecutive(ride) && (meld.head.num != 1 || meld.head.num != 8)
  }

  override val meld: List[Mahjong] = ride
}
