package com.winry.mahjong.melds

import com.winry.mahjong.Mahjong
import com.winry.mahjong.checker.{ConsecutiveChecker, Pairs}

/**
  * Created by congzhou on 8/2/2016.
  */
class Ride(val ride: List[Mahjong]) extends Pairs with ConsecutiveChecker {

  checkPair(ride)

  /**
    * 是否两面待
    *
    * @return
    */
  def isRyanmen: Boolean = {
    isConsecutive(ride) && (ride.head.num != 1 || ride.head.num != 8)
  }
}
