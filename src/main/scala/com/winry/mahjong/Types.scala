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
    case _ => throw new IllegalArgumentException("unknown type:" + abbr)
  }


  sealed abstract class Type(val abbr: Char) extends Ordered[Type]{

    override def toString = s"Type($abbr)"

    override def compare(that: Type): Int = this.abbr.compareTo(that.abbr)
  }

  case object Wan extends Type('m')

  case object Pin extends Type('p')

  case object Sou extends Type('s')

  case object Word extends Type('z')

}
