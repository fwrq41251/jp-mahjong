// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.winry.mahjong.message



@SerialVersionUID(0L)
final case class LoginResp(
    id: Long = 0L
    ) extends com.trueaccord.scalapb.GeneratedMessage with com.trueaccord.scalapb.Message[LoginResp] with com.trueaccord.lenses.Updatable[LoginResp] {
    @transient
    private[this] var __serializedSizeCachedValue: Int = 0
    private[this] def __computeSerializedValue(): Int = {
      var __size = 0
      if (id != 0L) { __size += _root_.com.google.protobuf.CodedOutputStream.computeInt64Size(1, id) }
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
      {
        val __v = id
        if (__v != 0L) {
          _output__.writeInt64(1, __v)
        }
      };
    }
    def mergeFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): com.winry.mahjong.message.LoginResp = {
      var __id = this.id
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case 8 =>
            __id = _input__.readInt64()
          case tag => _input__.skipField(tag)
        }
      }
      com.winry.mahjong.message.LoginResp(
          id = __id
      )
    }
    def withId(__v: Long): LoginResp = copy(id = __v)
    def getField(__field: _root_.com.google.protobuf.Descriptors.FieldDescriptor): scala.Any = {
      __field.getNumber match {
        case 1 => {
          val __t = id
          if (__t != 0L) __t else null
        }
      }
    }
    override def toString: String = _root_.com.trueaccord.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.winry.mahjong.message.LoginResp
}

object LoginResp extends com.trueaccord.scalapb.GeneratedMessageCompanion[com.winry.mahjong.message.LoginResp] {
  implicit def messageCompanion: com.trueaccord.scalapb.GeneratedMessageCompanion[com.winry.mahjong.message.LoginResp] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[_root_.com.google.protobuf.Descriptors.FieldDescriptor, scala.Any]): com.winry.mahjong.message.LoginResp = {
    require(__fieldsMap.keys.forall(_.getContainingType() == descriptor), "FieldDescriptor does not match message type.")
    val __fields = descriptor.getFields
    com.winry.mahjong.message.LoginResp(
      __fieldsMap.getOrElse(__fields.get(0), 0L).asInstanceOf[Long]
    )
  }
  def descriptor: _root_.com.google.protobuf.Descriptors.Descriptor = MessageProto.descriptor.getMessageTypes.get(2)
  def messageCompanionForField(__field: _root_.com.google.protobuf.Descriptors.FieldDescriptor): _root_.com.trueaccord.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__field)
  def enumCompanionForField(__field: _root_.com.google.protobuf.Descriptors.FieldDescriptor): _root_.com.trueaccord.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__field)
  lazy val defaultInstance = com.winry.mahjong.message.LoginResp(
  )
  implicit class LoginRespLens[UpperPB](_l: _root_.com.trueaccord.lenses.Lens[UpperPB, com.winry.mahjong.message.LoginResp]) extends _root_.com.trueaccord.lenses.ObjectLens[UpperPB, com.winry.mahjong.message.LoginResp](_l) {
    def id: _root_.com.trueaccord.lenses.Lens[UpperPB, Long] = field(_.id)((c_, f_) => c_.copy(id = f_))
  }
  final val ID_FIELD_NUMBER = 1
}
