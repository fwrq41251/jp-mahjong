package com.winry.mahjong.counter

import com.winry.mahjong.WinHands

/**
  * Created by User on 12/5/2016.
  */
object PointCounter {

  case class PointKey(fu: Int, han: Int, isOya: Boolean)

  //todo init map
  val pointMap: Map[PointKey, Int] = Map.empty

  def caculatePoint(fu: Int, han: Int, isOya: Boolean): Int = {
    val cHan = if (han >= 13) 13 else han
    val cFu = if (han >= 5) 0 else fu
    pointMap(PointKey(cFu, cHan, isOya))
  }

  def countFu(hands: WinHands): Int = {
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
