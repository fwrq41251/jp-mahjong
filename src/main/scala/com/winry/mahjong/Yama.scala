package com.winry.mahjong


import com.winry.mahjong.Types._

import scala.collection.mutable.ListBuffer
import scala.util.Random

/**
  * Created by User on 11/16/2016.
  */
class Yama {

  var index = 0
  var linshanIndex = 136
  var indicatorIndex = 132
  val mahjongs: List[Mahjong] = {
    var buffer = ListBuffer.empty[Mahjong]

    def appendDigits(typ: Type): Unit = {
      for (i <- 1 to 9; _ <- 1 to 4) {
        buffer += new Mahjong(typ, i)
      }
    }

    appendDigits(Wan)
    appendDigits(Pin)
    appendDigits(Sou)
    for (i <- 1 to 7; _ <- 1 to 4) {
      buffer += new Mahjong(Word, i)
    }
    Random.shuffle(buffer.toList)
  }
  var indicators: List[Mahjong] = List(mahjongs(indicatorIndex))

  def take(): Mahjong = {
    if (index > 122) {
      throw new IllegalStateException("no left mahjong to take!")
    } else {
      val toTake = mahjongs(index)
      index += 1
      toTake
    }
  }

  /**
    * 岭上
    *
    * @return
    */
  def rinshan(): Mahjong = {
    val toTake = mahjongs(linshanIndex)
    linshanIndex -= 1
    indicatorIndex -= 2
    indicators = indicators ::: List(mahjongs(indicatorIndex))
    toTake
  }


  override def toString = s"Yama($mahjongs)"
}
