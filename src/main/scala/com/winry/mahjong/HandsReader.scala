package com.winry.mahjong

import scala.collection.mutable.ListBuffer

/**
  * Created by congzhou on 7/30/2016.
  */
object HandsReader {

  def toHands(raw: String): Hands = {
    var temp = raw
    val listBuffer = ListBuffer.empty[Mahjong]
    for (ch <- temp; if ch.isLetter) {
      val index = temp.indexOf(ch)
      for (digit <- temp.take(index)) listBuffer += new Mahjong(Types.toType(ch), digit.asDigit)
      temp = temp.drop(index + 1)
    }
    if (listBuffer.size < 13) throw new IllegalArgumentException("hands have at least 13 mahjongs")
    new Hands(listBuffer.toList.sorted)
  }
}
