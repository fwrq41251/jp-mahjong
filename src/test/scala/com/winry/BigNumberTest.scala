package com.winry

import org.junit.Test

/**
  * Created by congzhou on 7/29/2016.
  */
class BigNumberTest {

  def print() = {
    var result = 6
  }

  def getFirstBit(number: Int): Int = {
    case num: Int if (num > 10) => getFirstBit(num / 10)
    case _ => number
  }

  @Test
  def getFirstBitTest(): Unit = {
    println(getFirstBit(56926))
  }
}
