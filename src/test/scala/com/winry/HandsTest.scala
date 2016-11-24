package com.winry

import com.winry.mahjong.Types.Pin
import com.winry.mahjong.yaku.MyChecker
import com.winry.mahjong._
import com.winry.mahjong.util.HandsReader
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
    val raw = "22m23345p667788s"
    val hands = new WinHands(HandsReader.toHands(raw), Mahjong(Pin, 4))
    println(new MyChecker(hands, new Game(Nil)))
  }

  @Test
  def nilTest(): Unit = {
    print(Nil.size)
  }

  @Test
  def yamaTest(): Unit = {
    val yama = new Yama()
    println(yama.mahjongs.size)
  }

}
