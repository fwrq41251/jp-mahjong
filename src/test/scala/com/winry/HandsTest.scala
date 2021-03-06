package com.winry

import com.winry.mahjong.Types.Pin
import com.winry.mahjong._
import com.winry.mahjong.counter.PointCounter
import com.winry.mahjong.message.LoginReq
import com.winry.mahjong.util.HandsReader
import com.winry.mahjong.yaku.MyChecker
import org.junit.{Assert, Test}


/**
  * Created by congzhou on 7/30/2016.
  */
class HandsTest {

  @Test
  def toHandsTest(): Unit = {
    val raw = "4298m679p5789s37z"
    val hands = HandsReader.toHands(raw)
    println(hands)
  }

  @Test
  def getDistanceTest(): Unit = {
    val raw = "22241p778899s33z"
    Assert.assertEquals(HandsReader.toHands(raw).getDistance, 1)
  }

  @Test
  def yakuCountTest(): Unit = {
    val raw = "22m23345p667788s"
    val hands = new WinHands(HandsReader.toHands(raw), Mahjong(Pin, 4))
    println(MyChecker.check(hands, new Game(Nil)))
  }

  @Test
  def yamaTest(): Unit = {
    val yama = new Yama()
    Assert.assertEquals(yama.mahjongs.size, 136)
  }

  @Test
  def PointCounterTest(): Unit = {
    Assert.assertEquals(PointCounter.caculatePoint(30, 1, isOya = true), 1500)
  }

  @Test
  def scalaPBTest(): Unit = {
    val loginReq = LoginReq("winry")
    val raw = loginReq.toByteArray
    val parsed = LoginReq.parseFrom(raw)
    println(parsed)
  }
}
