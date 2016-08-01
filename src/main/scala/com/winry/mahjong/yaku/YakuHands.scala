package com.winry.mahjong.yaku

import com.winry.mahjong._

/**
  * Created by congzhou on 8/1/2016.
  */
class YakuHands(override var mahjongs: List[Mahjong]) extends Hands(mahjongs) {

  var count = 0

  val chows: List[Chow] = Nil

  val pons: List[Pon] = Nil

  val eyes: List[Eye] = Nil

  def increaseBy(value: Int): Unit = {
    count += value
  }

}
