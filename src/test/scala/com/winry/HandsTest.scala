package com.winry

import com.winry.mahjong.Types.Wan
import com.winry.mahjong.{HandsReader, Mahjong, WinHands}
import com.winry.mahjong.yaku.MyChecker
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
  }

  @Test
  def yakuCountTest(): Unit = {
    val raw = "22m22234p667788s"
    val hands = WinHands(HandsReader.toHands(raw), Mahjong(Wan, 2))
    new MyChecker(hands).check()
    println(hands.yakuCount)
  }

  @Test
  def nilTest(): Unit = {
    print(Nil.size)
  }
}
