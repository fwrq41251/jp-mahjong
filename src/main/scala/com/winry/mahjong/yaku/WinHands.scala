package com.winry.mahjong.yaku

import com.winry.mahjong._

/**
  * Created by congzhou on 8/1/2016.
  */
class WinHands(mahjongs0: List[Mahjong]) extends Hands(mahjongs0) {

  if (mahjongs0.size != 14) throw new IllegalArgumentException("win hands must be composed of 14!")

  var count = 0

  val chows: List[Chow] = Nil

  val pons: List[Pon] = Nil

  val eye: Eye = null

  def increaseBy(value: Int): Unit = {
    count += value
  }

}
