package com.winry.mahjong

/**
  * Created by congzhou on 7/29/2016.
  */
object Types {

  def toType(abbr:String):Type = abbr match {
    case "m"=> Wan
    case "p"=> Pin
    case "s"=> Sou
    case "w"=> Wind
    case "d"=> Dragon
  }


  sealed abstract class Type(val abbr: String) {
  }

  case object Wan extends Type("m")

  case object Pin extends Type("p")

  case object Sou extends Type("s")

  case object Wind extends Type("w")

  case object Dragon extends Type("d")

}
