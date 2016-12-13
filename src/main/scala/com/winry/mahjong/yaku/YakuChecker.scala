package com.winry.mahjong.yaku

import com.winry.mahjong.Types.Word
import com.winry.mahjong.checker.{ChiChecker, PonChecker}
import com.winry.mahjong.counter.CountMahjong
import com.winry.mahjong.melds.{Chi, Eye, Pon}
import com.winry.mahjong.{Game, Mahjong, Types, WinHands}

import scala.collection.mutable.ListBuffer

/**
  * Created by congzhou on 8/1/2016.
  */
sealed abstract class YakuChecker(hands: WinHands, game: Game) extends Yaku {

  val next: Option[YakuChecker]

  def check(yakus: ListBuffer[Yaku]): List[Yaku] = {
    if (satisfy()) yakus += this
    next match {
      case Some(yakuChecker) => yakuChecker.check(yakus)
      case None => yakus.toList
    }
  }

  def satisfy(): Boolean

}

sealed abstract class CompoundYakuChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override def check(yakus: ListBuffer[Yaku]): List[Yaku] = {
    if (satisfy()) yakus += this
    next.get.next match {
      case Some(yakuChecker) => yakuChecker.check(yakus)
      case None => yakus.toList
    }
  }
}

trait Yaku {

  def name: String

  def value: Int

  override def toString = s"yaku($name, $value)"
}

object MyChecker {

  def check(hands: WinHands, game: Game): List[Yaku] = {
    new ReachChecker(hands, game).check(ListBuffer.empty)
  }
}

class ReachChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override val next = Some(new PinfuChecker(hands, game))

  override def value: Int = 1

  override def satisfy(): Boolean = {
    hands.isReach
  }

  override def name: String = "立直"
}

class PinfuChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override val next = None

  override def value: Int = 1

  override def satisfy(): Boolean = {
    hands.isClosed && hands.chis.size == 4 && hands.eye.isNoValue && hands.isRyanmen
  }

  override def name: String = "平和"
}

class IipeikouChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override def value: Int = 1

  override val next = None

  override def satisfy(): Boolean = {
    // convert chi list to set, if there is any duplicate element, set size will be small than list size.
    !hands.isChītoitsu && hands.isClosed && hands.chis.toSet.size != hands.chis.size
  }

  override def name: String = "一杯口"
}

class SanshokuDoujunChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override def value: Int = if (hands.isClosed) 2 else 1

  override val next = None

  override def satisfy(): Boolean = {
    val chis = hands.chis
    if (chis.size >= 3) {
      val map = chis.groupBy(_.typ)
      if (map.size == 3) {
        if (chis.size == 3) {
          chis.head.num == chis(1).num && chis(1).num == chis(2).num
        } else {
          // chis.size == 4
          val single = map.values.filter(_.size == 1).toList
          if (single.head == single(1)) map.values.filter(_.size > 1).toList.contains(single.head) else false
        }
      } else false
    } else false
  }

  override def name: String = "三色同顺"
}

class IkkitsuukanChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override def value: Int = if (hands.isClosed) 2 else 1

  override val next = None

  override def satisfy(): Boolean = {
    val chis = hands.chis
    if (chis.size >= 3) {
      val map = chis.groupBy(_.typ)
      if (map.values.exists(_.size >= 3)) {
        val ikki = map.values.filter(_.size > 3).toList.head
        val typ = ikki.head.typ
        ikki.contains(new Chi(typ, 1)) && ikki.contains(new Chi(typ, 4)) && ikki.contains(new Chi(typ, 7))
      } else false
    } else false
  }

  override def name: String = "一气通贯"
}

class RyanpeikouChecker(hands: WinHands, game: Game) extends CompoundYakuChecker(hands, game) {

  override def value: Int = 3

  override val next = Some(new IipeikouChecker(hands, game))

  override def satisfy(): Boolean = {
    val chis = hands.chis
    hands.isClosed && chis.size == 4 && chis.toSet.size == 2
  }

  override def name: String = "两杯口"
}

class ToitoihouChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override def value: Int = 2

  override val next = None

  override def satisfy(): Boolean = {
    (hands.pons.size + hands.kans.size) == 4
  }

  override def name: String = "对对和"
}

class SanshokudoukouChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override def value: Int = 2

  override val next = None

  override def satisfy(): Boolean = {
    val pons = hands.pons ::: hands.kans.map(_.toPon)
    if (pons.size >= 3) {
      val map = pons.groupBy(_.typ)
      if (map.values.exists(_.size >= 3)) {
        if (pons.size == 3) {
          pons.head.num == pons(1).num && pons(1).num == pons(2).num
        } else {
          // pons.size == 4
          val single = map.values.filter(_.size == 1).toList
          if (single.head == single(1)) map.values.filter(_.size > 1).toList.contains(single.head) else false
        }
      } else false
    } else false
  }

  override def name: String = "三色同刻"
}

class TanyaochuuChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override def value: Int = 1

  override val next = None

  override def satisfy(): Boolean = {
    hands.chis.forall(!_.isTaiYao) && hands.pons.forall(!_.isTaiYao) && !hands.eye.isTaiYao && hands.eyes.forall(!_
      .isTaiYao)
  }

  override def name: String = "断幺九"
}

class YakuhaiChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override def value: Int = 1

  override val next = None

  override def satisfy(): Boolean = {
    val yakuhai = List(new Pon(Word, 5), new Pon(Word, 6), new Pon(Word, 7))
    val pons = hands.pons ::: hands.kans.map(_.toPon)
    pons.exists(yakuhai.contains)
  }

  override def name: String = "役牌"
}

class SanankoChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override def value: Int = 2

  override val next = None

  override def satisfy(): Boolean = {
    val pons = hands.pons ::: hands.kans.map(_.toPon)
    pons.count(_.isClosed) >= 3
  }

  override def name: String = "三暗刻"
}

class ChantaiyaoChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) with ChiChecker with PonChecker {

  override def value: Int = if (hands.isClosed) 2 else 1

  override val next = None

  override def satisfy(): Boolean = {
    !hands.isChītoitsu && hands.chis.forall(_.isTaiYao) && hands.pons.forall(_.isTaiYao) && hands.eye.isTaiYao &&
      hands.kans.forall(_.isTaiYao)
  }

  override def name: String = "全带"
}

class JunchantaiyaoChecker(hands: WinHands, game: Game) extends CompoundYakuChecker(hands, game) {

  override def value: Int = if (hands.isClosed) 3 else 2

  override val next: Option[YakuChecker] = Some(new ChantaiyaoChecker(hands, game))

  override def satisfy(): Boolean = {
    !hands.isChītoitsu && hands.chis.forall(_.isTaiYao) && hands.pons.forall(_.isJunTaiYao) && hands.eye.isJunTaiYao &&
      hands.kans.forall(_.isJunTaiYao)
  }

  override def name: String = "纯全带"
}

class HonroutouChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override def value: Int = 2

  override val next: Option[YakuChecker] = ???

  override def satisfy(): Boolean = {
    if (hands.isChītoitsu) {
      hands.eyes.forall(_.isTaiYao)
    } else {
      hands.chis.isEmpty && hands.pons.forall(_.isTaiYao) && hands.eye.isTaiYao && hands.kans.forall(_.isTaiYao)
    }
  }

  override def name: String = "混老头"
}

class ShousangenChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override def value: Int = 2

  override val next: Option[YakuChecker] = ???

  override def satisfy(): Boolean = {
    val j = Mahjong(Types.Word, 5)
    val h = Mahjong(Types.Word, 6)
    val p = Mahjong(Types.Word, 7)
    val shousangen = List((List(j, h), p), (List(j, p), h), (List(h, p), j))
    val pons = hands.pons ::: hands.kans.map(_.toPon)
    shousangen.exists(s => pons.contains(new Pon(s._1.head)) && hands.pons.contains(new Pon(s._1(1)
    )) && hands.eye == new Eye(s._2))
  }

  override def name: String = "小三元"
}

class HoniisouChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override def value: Int = if (hands.isClosed) 3 else 2

  override val next: Option[YakuChecker] = ???

  override def satisfy(): Boolean = {
    if (hands.isChītoitsu) {
      val typ = hands.eyes.head.typ
      hands.eyes.tail.forall(e => e.typ == typ || e.typ == Types.Word)
    } else {
      val typ = hands.eye.typ
      hands.chis.forall(c => c.typ == typ || c.typ == Types.Word) && hands.pons.forall(c => c.typ == typ || c.typ ==
        Types.Word) && hands.kans.forall(c => c.typ == typ || c.typ == Types.Word)
    }
  }

  override def name: String = "混一色"
}

class ChiniisouChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override def value: Int = if (hands.isClosed) 6 else 5

  override val next: Option[YakuChecker] = ???

  override def satisfy(): Boolean = {
    if (hands.isChītoitsu) {
      val typ = hands.eyes.head.typ
      hands.eyes.tail.forall(e => e.typ == typ)
    } else {
      val typ = hands.eye.typ
      hands.chis.forall(_.typ == typ) && hands.pons.forall(_.typ == typ) && hands.kans.forall(_.typ == typ)
    }
  }

  override def name: String = "清一色"
}

class SuuankouChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override val next: Option[YakuChecker] = _

  override def satisfy(): Boolean = {
    hands.isClosed && hands.pons.size == 4 && !hands.isTanki
  }

  override def name: String = "四暗刻"

  override def value: Int = 13
}

class DaisangenChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override val next: Option[YakuChecker] = _

  override def satisfy(): Boolean = {
    hands.pons.contains(new Pon(Types.Word, 5)) && hands.pons.contains(new Pon(Types.Word, 6)) &&
      hands.pons.contains(new Pon(Types.Word, 7))
  }

  override def name: String = "大三元"

  override def value: Int = 13
}

class ShousuushiiChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override val next: Option[YakuChecker] = _

  override def satisfy(): Boolean = {
    val suushii = List(Mahjong(Types.Word, 1), Mahjong(Types.Word, 2), Mahjong(Types.Word, 3), Mahjong(Types.Word, 4))
    val shousuushii = for (m <- suushii) yield {
      suushii.filter(s => !(s == m)).map(new Pon(_)) -> m
    }
    shousuushii.exists(s => hands.pons.containsSlice(s._1) && hands.eye == new Eye(s._2))
  }

  override def name: String = "小四喜"

  override def value: Int = 13
}

class DaisuushiiChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override val next: Option[YakuChecker] = _

  override def satisfy(): Boolean = {
    val suushii = List(new Pon(Types.Word, 1), new Pon(Types.Word, 2), new Pon(Types.Word, 3), new Pon(Types.Word, 4))
    hands.pons.containsSlice(suushii)
  }

  override def name: String = "大四喜"

  override def value: Int = 14
}

class TsuuiisouChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override val next: Option[YakuChecker] = _

  override def satisfy(): Boolean = {
    hands.pons.size == 4 && hands.pons.forall(_.typ == Types.Word) && hands.eye.typ == Types.Word
  }

  override def name: String = "字一色"

  override def value: Int = 14
}

class DaichiseiChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override val next: Option[YakuChecker] = _

  override def satisfy(): Boolean = {
    hands.isChītoitsu && hands.eyes.forall(_.typ == Types.Word)
  }

  override def name: String = "大七星"

  override def value: Int = 14
}

class ChinroutouChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override val next: Option[YakuChecker] = _

  override def satisfy(): Boolean = {
    if (hands.isChītoitsu) {
      hands.eyes.forall(_.isTaiYao)
    } else {
      hands.pons.size == 4 && hands.pons.forall(_.isTaiYao) && hands.eye.isTaiYao
    }
  }

  override def name: String = "清老头"

  override def value: Int = 14
}

class ChuurenpoutouChecker(hands: WinHands, game: Game) extends YakuChecker(hands, game) {

  override val next: Option[YakuChecker] = _

  override def satisfy(): Boolean = !hands.isChītoitsu && hands.toCount.groupBy(_.typ).size == 1 && (1 to 9).forall(i
  => hands.toCount.contains(new CountMahjong(Mahjong(hands.toCount.head.typ, i))))

  override def name: String = "九莲宝灯"

  override def value: Int = 14
}