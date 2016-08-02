package com.winry

import com.winry.mahjong.{HandsReader, WinHands}
import com.winry.mahjong.counter.YakuCounter
import org.junit.Test


/**
  * Created by congzhou on 7/30/2016.
  */
class HandsTest {

  @Test
  def toHandsTest(): Unit = {
    val raw = "4298m679p5789s37z"
    println(HandsReader.toHands(raw))
  }

  @Test
  def getDistanceTest(): Unit = {
    val raw = "22221p778899s33z"
    println(HandsReader.toHands(raw).getDistance)
  }

  @Test
  def toWinHandsTest(): Unit = {
    val raw = "122223p778899s33z"
    println(WinHands(HandsReader.toHands(raw)))
  }

  @Test
  def yakuCountTest(): Unit = {
    val raw = "22m222234p667788s"
    val hands = WinHands(HandsReader.toHands(raw))
    println(YakuCounter(hands))
  }
}
