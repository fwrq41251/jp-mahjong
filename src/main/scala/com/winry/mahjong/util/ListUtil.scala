package com.winry.mahjong.util

/**
  * Created by congzhou on 8/16/2016.
  */
object ListUtil {

  def removeSubList[A](l: List[A], sublist: List[A]): List[A] = l.indexOfSlice(sublist) match {
    case -1 => l
    case index => removeSubList(l.patch(index, Nil, sublist.length), sublist)
  }

}
