package com.winry.mahjong

/**
 * Created by cong on 2016/8/1.
 */
object ListUtil {

  def removeSubList[A](l: List[A], sublist: List[A]): List[A] = l.indexOfSlice(sublist) match {
    case -1 => l
    case index => removeSubList(l.patch(index, Nil, sublist.length), sublist)
  }
}
