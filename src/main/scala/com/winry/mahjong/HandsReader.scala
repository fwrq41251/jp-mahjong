package com.winry.mahjong

import scala.collection.mutable.ListBuffer

/**
  * Created by congzhou on 7/30/2016.
  */
object HandsReader {

  def toHands(raw: String): Hands = {
    //fixme this should be refined later,if types are in different order,this will not work
    var temp = raw
    val listBuffer = ListBuffer.empty[Mahjong]
    val splitter = List('m', 'p', 's', 'z')
    for (split <- splitter) {
      val index = temp.indexOf(split)
      if (index != -1) {
        for (num <- temp.substring(0, index)) {
          listBuffer += new Mahjong(Types.toType(split), num.asDigit)
        }
        temp = temp.drop(index + 1)
      }
    }
    if(listBuffer.size != 13) throw new IllegalArgumentException("hands must be of 13 mahjongs")
    new Hands(listBuffer.toList.sorted)
  }
}
