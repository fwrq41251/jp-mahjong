package com.winry

import com.winry.mahjong.HandsReader
import org.junit.Test


/**
  * Created by congzhou on 7/30/2016.
  */
class HandsTest {

  @Test
  def toHandsTest(): Unit = {
    val raw = "4298m679p5789s37z";
    println(HandsReader.toHands(raw))
  }

  @Test
  def getDistanceTest(): Unit = {
    val raw = "345m666p78889s37z";
    println(HandsReader.toHands(raw).getDistance)
  }
}
