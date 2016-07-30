package com.winry.mahjong

/**
  * Created by congzhou on 7/29/2016.
  */
object Types {

  def toType(abbr: Char): Type = abbr match {
    case 'm' => Wan
    case 'p' => Pin
    case 's' => Sou
    case 'z' => Word
  }


  sealed abstract class Type(val abbr: Char) {

    override def toString = s"Type($abbr)"
  }

  case object Wan extends Type('m')

  case object Pin extends Type('p')

  case object Sou extends Type('s')

  case object Word extends Type('z')

}
