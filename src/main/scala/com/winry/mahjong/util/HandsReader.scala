package com.winry.mahjong.util

import com.winry.mahjong.{Hands, Mahjong, Types}

import scala.collection.mutable.ListBuffer

/**
  * Created by congzhou on 7/30/2016.
  */
object HandsReader {

  def toHands(raw: String): Hands = {
    def toHandsHelper(temp: List[Int], raw: List[Char], mahjongs: List[Mahjong]): Hands = {
      raw match {
        case x :: xs => if (x.isLetter) {
          val typ = Types.toType(x)
          toHandsHelper(List(), xs, temp.map(i => new Mahjong(typ, i)) ::: mahjongs)
        } else toHandsHelper(x.asDigit :: temp, xs, mahjongs)
        case Nil => new Hands(mahjongs.sorted)
      }
    }

    toHandsHelper(Nil, raw.toList, Nil)
  }
}
