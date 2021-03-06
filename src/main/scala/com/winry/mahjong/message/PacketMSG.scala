// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.winry.mahjong.message



@SerialVersionUID(0L)
final case class PacketMSG(
    msg: com.winry.mahjong.message.PacketMSG.Msg = com.winry.mahjong.message.PacketMSG.Msg.Empty
    ) extends com.trueaccord.scalapb.GeneratedMessage with com.trueaccord.scalapb.Message[PacketMSG] with com.trueaccord.lenses.Updatable[PacketMSG] {
    @transient
    private[this] var __serializedSizeCachedValue: Int = 0
    private[this] def __computeSerializedValue(): Int = {
      var __size = 0
      if (msg.loginReq.isDefined) { __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(msg.loginReq.get.serializedSize) + msg.loginReq.get.serializedSize }
      if (msg.readyReq.isDefined) { __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(msg.readyReq.get.serializedSize) + msg.readyReq.get.serializedSize }
      if (msg.reachReq.isDefined) { __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(msg.reachReq.get.serializedSize) + msg.reachReq.get.serializedSize }
      if (msg.tsumoReq.isDefined) { __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(msg.tsumoReq.get.serializedSize) + msg.tsumoReq.get.serializedSize }
      if (msg.discardReq.isDefined) { __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(msg.discardReq.get.serializedSize) + msg.discardReq.get.serializedSize }
      if (msg.heartbeat.isDefined) { __size += 1 + _root_.com.google.protobuf.CodedOutputStream.computeUInt32SizeNoTag(msg.heartbeat.get.serializedSize) + msg.heartbeat.get.serializedSize }
      __size
    }
    final override def serializedSize: Int = {
      var read = __serializedSizeCachedValue
      if (read == 0) {
        read = __computeSerializedValue()
        __serializedSizeCachedValue = read
      }
      read
    }
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): Unit = {
      msg.loginReq.foreach { __v =>
        _output__.writeTag(1, 2)
        _output__.writeUInt32NoTag(__v.serializedSize)
        __v.writeTo(_output__)
      };
      msg.readyReq.foreach { __v =>
        _output__.writeTag(2, 2)
        _output__.writeUInt32NoTag(__v.serializedSize)
        __v.writeTo(_output__)
      };
      msg.reachReq.foreach { __v =>
        _output__.writeTag(3, 2)
        _output__.writeUInt32NoTag(__v.serializedSize)
        __v.writeTo(_output__)
      };
      msg.tsumoReq.foreach { __v =>
        _output__.writeTag(4, 2)
        _output__.writeUInt32NoTag(__v.serializedSize)
        __v.writeTo(_output__)
      };
      msg.discardReq.foreach { __v =>
        _output__.writeTag(5, 2)
        _output__.writeUInt32NoTag(__v.serializedSize)
        __v.writeTo(_output__)
      };
      msg.heartbeat.foreach { __v =>
        _output__.writeTag(6, 2)
        _output__.writeUInt32NoTag(__v.serializedSize)
        __v.writeTo(_output__)
      };
    }
    def mergeFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): com.winry.mahjong.message.PacketMSG = {
      var __msg = this.msg
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 10 =>
            __msg = com.winry.mahjong.message.PacketMSG.Msg.LoginReq(_root_.com.trueaccord.scalapb.LiteParser.readMessage(_input__, msg.loginReq.getOrElse(com.winry.mahjong.message.LoginReq.defaultInstance)))
          case 18 =>
            __msg = com.winry.mahjong.message.PacketMSG.Msg.ReadyReq(_root_.com.trueaccord.scalapb.LiteParser.readMessage(_input__, msg.readyReq.getOrElse(com.winry.mahjong.message.ReadyReq.defaultInstance)))
          case 26 =>
            __msg = com.winry.mahjong.message.PacketMSG.Msg.ReachReq(_root_.com.trueaccord.scalapb.LiteParser.readMessage(_input__, msg.reachReq.getOrElse(com.winry.mahjong.message.ReachReq.defaultInstance)))
          case 34 =>
            __msg = com.winry.mahjong.message.PacketMSG.Msg.TsumoReq(_root_.com.trueaccord.scalapb.LiteParser.readMessage(_input__, msg.tsumoReq.getOrElse(com.winry.mahjong.message.TsumoReq.defaultInstance)))
          case 42 =>
            __msg = com.winry.mahjong.message.PacketMSG.Msg.DiscardReq(_root_.com.trueaccord.scalapb.LiteParser.readMessage(_input__, msg.discardReq.getOrElse(com.winry.mahjong.message.DiscardReq.defaultInstance)))
          case 50 =>
            __msg = com.winry.mahjong.message.PacketMSG.Msg.Heartbeat(_root_.com.trueaccord.scalapb.LiteParser.readMessage(_input__, msg.heartbeat.getOrElse(com.winry.mahjong.message.Heartbeat.defaultInstance)))
          case tag => _input__.skipField(tag)
        }
      }
      com.winry.mahjong.message.PacketMSG(
          msg = __msg
      )
    }
    def getLoginReq: com.winry.mahjong.message.LoginReq = msg.loginReq.getOrElse(com.winry.mahjong.message.LoginReq.defaultInstance)
    def withLoginReq(__v: com.winry.mahjong.message.LoginReq): PacketMSG = copy(msg = com.winry.mahjong.message.PacketMSG.Msg.LoginReq(__v))
    def getReadyReq: com.winry.mahjong.message.ReadyReq = msg.readyReq.getOrElse(com.winry.mahjong.message.ReadyReq.defaultInstance)
    def withReadyReq(__v: com.winry.mahjong.message.ReadyReq): PacketMSG = copy(msg = com.winry.mahjong.message.PacketMSG.Msg.ReadyReq(__v))
    def getReachReq: com.winry.mahjong.message.ReachReq = msg.reachReq.getOrElse(com.winry.mahjong.message.ReachReq.defaultInstance)
    def withReachReq(__v: com.winry.mahjong.message.ReachReq): PacketMSG = copy(msg = com.winry.mahjong.message.PacketMSG.Msg.ReachReq(__v))
    def getTsumoReq: com.winry.mahjong.message.TsumoReq = msg.tsumoReq.getOrElse(com.winry.mahjong.message.TsumoReq.defaultInstance)
    def withTsumoReq(__v: com.winry.mahjong.message.TsumoReq): PacketMSG = copy(msg = com.winry.mahjong.message.PacketMSG.Msg.TsumoReq(__v))
    def getDiscardReq: com.winry.mahjong.message.DiscardReq = msg.discardReq.getOrElse(com.winry.mahjong.message.DiscardReq.defaultInstance)
    def withDiscardReq(__v: com.winry.mahjong.message.DiscardReq): PacketMSG = copy(msg = com.winry.mahjong.message.PacketMSG.Msg.DiscardReq(__v))
    def getHeartbeat: com.winry.mahjong.message.Heartbeat = msg.heartbeat.getOrElse(com.winry.mahjong.message.Heartbeat.defaultInstance)
    def withHeartbeat(__v: com.winry.mahjong.message.Heartbeat): PacketMSG = copy(msg = com.winry.mahjong.message.PacketMSG.Msg.Heartbeat(__v))
    def clearMsg: PacketMSG = copy(msg = com.winry.mahjong.message.PacketMSG.Msg.Empty)
    def withMsg(__v: com.winry.mahjong.message.PacketMSG.Msg): PacketMSG = copy(msg = __v)
    def getField(__field: _root_.com.google.protobuf.Descriptors.FieldDescriptor): scala.Any = {
      __field.getNumber match {
        case 1 => msg.loginReq.getOrElse(null)
        case 2 => msg.readyReq.getOrElse(null)
        case 3 => msg.reachReq.getOrElse(null)
        case 4 => msg.tsumoReq.getOrElse(null)
        case 5 => msg.discardReq.getOrElse(null)
        case 6 => msg.heartbeat.getOrElse(null)
      }
    }
    override def toString: String = _root_.com.trueaccord.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.winry.mahjong.message.PacketMSG
}

object PacketMSG extends com.trueaccord.scalapb.GeneratedMessageCompanion[com.winry.mahjong.message.PacketMSG] {
  implicit def messageCompanion: com.trueaccord.scalapb.GeneratedMessageCompanion[com.winry.mahjong.message.PacketMSG] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[_root_.com.google.protobuf.Descriptors.FieldDescriptor, scala.Any]): com.winry.mahjong.message.PacketMSG = {
    require(__fieldsMap.keys.forall(_.getContainingType() == descriptor), "FieldDescriptor does not match message type.")
    val __fields = descriptor.getFields
    com.winry.mahjong.message.PacketMSG(
      msg = __fieldsMap.get(__fields.get(0)).asInstanceOf[scala.Option[com.winry.mahjong.message.LoginReq]].map(com.winry.mahjong.message.PacketMSG.Msg.LoginReq(_)) orElse
__fieldsMap.get(__fields.get(1)).asInstanceOf[scala.Option[com.winry.mahjong.message.ReadyReq]].map(com.winry.mahjong.message.PacketMSG.Msg.ReadyReq(_)) orElse
__fieldsMap.get(__fields.get(2)).asInstanceOf[scala.Option[com.winry.mahjong.message.ReachReq]].map(com.winry.mahjong.message.PacketMSG.Msg.ReachReq(_)) orElse
__fieldsMap.get(__fields.get(3)).asInstanceOf[scala.Option[com.winry.mahjong.message.TsumoReq]].map(com.winry.mahjong.message.PacketMSG.Msg.TsumoReq(_)) orElse
__fieldsMap.get(__fields.get(4)).asInstanceOf[scala.Option[com.winry.mahjong.message.DiscardReq]].map(com.winry.mahjong.message.PacketMSG.Msg.DiscardReq(_)) orElse
__fieldsMap.get(__fields.get(5)).asInstanceOf[scala.Option[com.winry.mahjong.message.Heartbeat]].map(com.winry.mahjong.message.PacketMSG.Msg.Heartbeat(_)) getOrElse com.winry.mahjong.message.PacketMSG.Msg.Empty
    )
  }
  def descriptor: _root_.com.google.protobuf.Descriptors.Descriptor = MessageProto.descriptor.getMessageTypes.get(0)
  def messageCompanionForField(__field: _root_.com.google.protobuf.Descriptors.FieldDescriptor): _root_.com.trueaccord.scalapb.GeneratedMessageCompanion[_] = {
    require(__field.getContainingType() == descriptor, "FieldDescriptor does not match message type.")
    var __out: _root_.com.trueaccord.scalapb.GeneratedMessageCompanion[_] = null
    __field.getNumber match {
      case 1 => __out = com.winry.mahjong.message.LoginReq
      case 2 => __out = com.winry.mahjong.message.ReadyReq
      case 3 => __out = com.winry.mahjong.message.ReachReq
      case 4 => __out = com.winry.mahjong.message.TsumoReq
      case 5 => __out = com.winry.mahjong.message.DiscardReq
      case 6 => __out = com.winry.mahjong.message.Heartbeat
    }
  __out
  }
  def enumCompanionForField(__field: _root_.com.google.protobuf.Descriptors.FieldDescriptor): _root_.com.trueaccord.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__field)
  lazy val defaultInstance = com.winry.mahjong.message.PacketMSG(
  )
  sealed trait Msg extends _root_.com.trueaccord.scalapb.GeneratedOneof {
    def isEmpty: Boolean = false
    def isDefined: Boolean = true
    def number: Int
    def isLoginReq: Boolean = false
    def isReadyReq: Boolean = false
    def isReachReq: Boolean = false
    def isTsumoReq: Boolean = false
    def isDiscardReq: Boolean = false
    def isHeartbeat: Boolean = false
    def loginReq: scala.Option[com.winry.mahjong.message.LoginReq] = None
    def readyReq: scala.Option[com.winry.mahjong.message.ReadyReq] = None
    def reachReq: scala.Option[com.winry.mahjong.message.ReachReq] = None
    def tsumoReq: scala.Option[com.winry.mahjong.message.TsumoReq] = None
    def discardReq: scala.Option[com.winry.mahjong.message.DiscardReq] = None
    def heartbeat: scala.Option[com.winry.mahjong.message.Heartbeat] = None
  }
  object Msg extends {
    @SerialVersionUID(0L)
    case object Empty extends com.winry.mahjong.message.PacketMSG.Msg {
      override def isEmpty: Boolean = true
      override def isDefined: Boolean = false
      override def number: Int = 0
    }
  
    @SerialVersionUID(0L)
    case class LoginReq(value: com.winry.mahjong.message.LoginReq) extends com.winry.mahjong.message.PacketMSG.Msg {
      override def isLoginReq: Boolean = true
      override def loginReq: scala.Option[com.winry.mahjong.message.LoginReq] = Some(value)
      override def number: Int = 1
    }
    @SerialVersionUID(0L)
    case class ReadyReq(value: com.winry.mahjong.message.ReadyReq) extends com.winry.mahjong.message.PacketMSG.Msg {
      override def isReadyReq: Boolean = true
      override def readyReq: scala.Option[com.winry.mahjong.message.ReadyReq] = Some(value)
      override def number: Int = 2
    }
    @SerialVersionUID(0L)
    case class ReachReq(value: com.winry.mahjong.message.ReachReq) extends com.winry.mahjong.message.PacketMSG.Msg {
      override def isReachReq: Boolean = true
      override def reachReq: scala.Option[com.winry.mahjong.message.ReachReq] = Some(value)
      override def number: Int = 3
    }
    @SerialVersionUID(0L)
    case class TsumoReq(value: com.winry.mahjong.message.TsumoReq) extends com.winry.mahjong.message.PacketMSG.Msg {
      override def isTsumoReq: Boolean = true
      override def tsumoReq: scala.Option[com.winry.mahjong.message.TsumoReq] = Some(value)
      override def number: Int = 4
    }
    @SerialVersionUID(0L)
    case class DiscardReq(value: com.winry.mahjong.message.DiscardReq) extends com.winry.mahjong.message.PacketMSG.Msg {
      override def isDiscardReq: Boolean = true
      override def discardReq: scala.Option[com.winry.mahjong.message.DiscardReq] = Some(value)
      override def number: Int = 5
    }
    @SerialVersionUID(0L)
    case class Heartbeat(value: com.winry.mahjong.message.Heartbeat) extends com.winry.mahjong.message.PacketMSG.Msg {
      override def isHeartbeat: Boolean = true
      override def heartbeat: scala.Option[com.winry.mahjong.message.Heartbeat] = Some(value)
      override def number: Int = 6
    }
  }
  implicit class PacketMSGLens[UpperPB](_l: _root_.com.trueaccord.lenses.Lens[UpperPB, com.winry.mahjong.message.PacketMSG]) extends _root_.com.trueaccord.lenses.ObjectLens[UpperPB, com.winry.mahjong.message.PacketMSG](_l) {
    def loginReq: _root_.com.trueaccord.lenses.Lens[UpperPB, com.winry.mahjong.message.LoginReq] = field(_.getLoginReq)((c_, f_) => c_.copy(msg = com.winry.mahjong.message.PacketMSG.Msg.LoginReq(f_)))
    def readyReq: _root_.com.trueaccord.lenses.Lens[UpperPB, com.winry.mahjong.message.ReadyReq] = field(_.getReadyReq)((c_, f_) => c_.copy(msg = com.winry.mahjong.message.PacketMSG.Msg.ReadyReq(f_)))
    def reachReq: _root_.com.trueaccord.lenses.Lens[UpperPB, com.winry.mahjong.message.ReachReq] = field(_.getReachReq)((c_, f_) => c_.copy(msg = com.winry.mahjong.message.PacketMSG.Msg.ReachReq(f_)))
    def tsumoReq: _root_.com.trueaccord.lenses.Lens[UpperPB, com.winry.mahjong.message.TsumoReq] = field(_.getTsumoReq)((c_, f_) => c_.copy(msg = com.winry.mahjong.message.PacketMSG.Msg.TsumoReq(f_)))
    def discardReq: _root_.com.trueaccord.lenses.Lens[UpperPB, com.winry.mahjong.message.DiscardReq] = field(_.getDiscardReq)((c_, f_) => c_.copy(msg = com.winry.mahjong.message.PacketMSG.Msg.DiscardReq(f_)))
    def heartbeat: _root_.com.trueaccord.lenses.Lens[UpperPB, com.winry.mahjong.message.Heartbeat] = field(_.getHeartbeat)((c_, f_) => c_.copy(msg = com.winry.mahjong.message.PacketMSG.Msg.Heartbeat(f_)))
    def msg: _root_.com.trueaccord.lenses.Lens[UpperPB, com.winry.mahjong.message.PacketMSG.Msg] = field(_.msg)((c_, f_) => c_.copy(msg = f_))
  }
  final val LOGINREQ_FIELD_NUMBER = 1
  final val READYREQ_FIELD_NUMBER = 2
  final val REACHREQ_FIELD_NUMBER = 3
  final val TSUMOREQ_FIELD_NUMBER = 4
  final val DISCARDREQ_FIELD_NUMBER = 5
  final val HEARTBEAT_FIELD_NUMBER = 6
}
