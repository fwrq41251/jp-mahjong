package com.winry.mahjong.yaku

import com.winry.mahjong._
import com.winry.mahjong.checker.{ChowChecker, PonChecker}

import scala.collection.mutable.ListBuffer

/**
  * Created by congzhou on 8/1/2016.
  */
class WinHands(var mahjongs: List[Mahjong]) extends ChowChecker with PonChecker {

  if (mahjongs.size != 14) throw new IllegalArgumentException("win hands must be composed of 14!")
  val chowBuffer = ListBuffer.empty[Chow]
  var temp = mahjongs.filter(m => m.typ != Types.Word).distinct
  while (temp.size >= 3) {
    val toCount = temp.take(3)
    if (isChow(toCount)) {
      chowBuffer += new Chow(toCount)
      mahjongs = ListUtil.removeSubList(mahjongs, toCount)
    }
    temp = temp.drop(1)
  }
  val ponBuffer = ListBuffer.empty[Pon]
  temp = mahjongs
  while (temp.size >= 3) {
    val toCount = temp.take(3)
    if (isPon(toCount)) {
      ponBuffer += new Pon(toCount)
      mahjongs = ListUtil.removeSubList(mahjongs, toCount)
    }
    temp = temp.drop(1)
  }

  var yakuCount = 0

  val chows: List[Chow] = chowBuffer.toList

  val pons: List[Pon] = ponBuffer.toList

  val eye: Eye = new Eye(mahjongs)

  var isReach = false

  def add(value: Int): Unit = {
    yakuCount += value
  }

}

