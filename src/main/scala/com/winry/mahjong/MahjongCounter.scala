package com.winry.mahjong

/**
  * Created by congzhou on 7/29/2016.
  */
class MahjongCounter(var mahjongs: List[Mahjong]) {

  var uncountMahjongs: List[Mahjong] = mahjongs

  var toCount: List[Mahjong] = Nil

  def countDistance: Int = {
    val distance = 8
    distance - (countChows - countPons) * 2 - countEyes - countRides
  }

  private def count(size: Int, predicate: => Boolean): Int = {
    var count: Int = 0
    var temp = uncountMahjongs
    while (temp.size >= size) {
      toCount = temp.take(size)
      if (predicate) {
        count = count + 1
        uncountMahjongs = removeSubList(uncountMahjongs, toCount)
      }
      temp = temp.drop(1)
    }
    count
  }

  def countChows: Int = {
    count(3, isChow)
  }

  def countPons: Int = {
    count(3, isPon)
  }

  def countEyes: Int = {
    count(2, isEye)
  }


  def countRides: Int = {
    count(2, isRide)
  }

  def removeSubList[A](l: List[A], sublist: List[A]): List[A] = l.indexOfSlice(sublist) match {
    case -1 => l
    case index => removeSubList(l.patch(index, Nil, sublist.length), sublist)
  }

  def isChow: Boolean = {
    isConsecutive(toCount(0), toCount(1)) && isConsecutive(toCount(1), toCount(2))
  }


  def isPon: Boolean = {
    toCount(0) == toCount(1) && toCount(1) == toCount(2)
  }

  def isEye: Boolean = {
    toCount(0) == toCount(1)
  }

  def isConsecutive(one: Mahjong, two: Mahjong): Boolean = one.typ == two.typ && one.num + 1 == two.num

  def isRide: Boolean = {
    def isStep(one: Mahjong, two: Mahjong): Boolean = one.typ == two.typ && one.num + 2 == two.num
    isEye || isConsecutive(toCount(0), toCount(1)) || isStep(toCount(0), toCount(1))
  }
}
