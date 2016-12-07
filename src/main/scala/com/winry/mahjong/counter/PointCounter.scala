package com.winry.mahjong.counter

import com.google.common.base.{Charsets, Splitter}
import com.google.common.io.Resources
import com.winry.mahjong.WinHands

import scala.collection.mutable

/**
  * Created by User on 12/5/2016.
  */
object PointCounter {

  case class PointKey(fu: Int, han: Int, isOya: Boolean)

  /**
    * key -> point
    */
  val pointMap: mutable.Map[PointKey, Int] = {
    val map = mutable.Map.empty[PointKey, Int]
    val url = Resources.getResource("PointTable")
    val lines = Resources.toString(url, Charsets.UTF_8).split("\n").tail
    lines.foreach(line => {
      val iterator = Splitter.on(' ').trimResults().omitEmptyStrings().split(line).iterator()
      val han = iterator.next().toInt
      val fu = iterator.next().toInt
      val isOya = iterator.next().toBoolean
      val point = iterator.next().toInt
      map += PointKey(fu, han, isOya) -> point
    })
    map
  }


  def caculatePoint(fu: Int, han: Int, isOya: Boolean): Int = {
    val cHan = if (han >= 13) 13 else han
    val cFu = if (han >= 5) 0 else fu
    pointMap(PointKey(cFu, cHan, isOya))
  }

  def countFu(hands: WinHands): Int = {
    if (hands.isChītoitsu) 25 else {
      val ponsFu = hands.pons.foldLeft(0)((z, i) => {
        val fu = if (i.isClosed) {
          if (i.isTaiYao) 8 else 4
        } else {
          if (i.isTaiYao) 4 else 2
        }
        z + fu
      })
      val kansFu = hands.kans.foldLeft(0)((z, i) => {
        val fu = if (i.isClosed) {
          if (i.isTaiYao) 32 else 16
        } else {
          if (i.isTaiYao) 16 else 8
        }
        z + fu
      })
      val waitsFu = {
        if (hands.isTanki) {
          2
        } else {
          if (hands.isRyanmen) 0 else 2
        }
      }
      20 + ponsFu + kansFu + waitsFu
    }
  }

}
