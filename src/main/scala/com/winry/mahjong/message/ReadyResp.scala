// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package com.winry.mahjong.message



@SerialVersionUID(0L)
final case class ReadyResp(
    ) extends com.trueaccord.scalapb.GeneratedMessage with com.trueaccord.scalapb.Message[ReadyResp] with com.trueaccord.lenses.Updatable[ReadyResp] {
    final override def serializedSize: Int = 0
    def writeTo(`_output__`: _root_.com.google.protobuf.CodedOutputStream): Unit = {
    }
    def mergeFrom(`_input__`: _root_.com.google.protobuf.CodedInputStream): com.winry.mahjong.message.ReadyResp = {
      var _done__ = false
      while (!_done__) {
        val _tag__ = _input__.readTag()
        _tag__ match {
          case 0 => _done__ = true
          case tag => _input__.skipField(tag)
        }
      }
      com.winry.mahjong.message.ReadyResp(
      )
    }
    def getField(__field: _root_.com.google.protobuf.Descriptors.FieldDescriptor): scala.Any = throw new MatchError(__field)
    override def toString: String = _root_.com.trueaccord.scalapb.TextFormat.printToUnicodeString(this)
    def companion = com.winry.mahjong.message.ReadyResp
}

object ReadyResp extends com.trueaccord.scalapb.GeneratedMessageCompanion[com.winry.mahjong.message.ReadyResp] {
  implicit def messageCompanion: com.trueaccord.scalapb.GeneratedMessageCompanion[com.winry.mahjong.message.ReadyResp] = this
  def fromFieldsMap(__fieldsMap: scala.collection.immutable.Map[_root_.com.google.protobuf.Descriptors.FieldDescriptor, scala.Any]): com.winry.mahjong.message.ReadyResp = {
    require(__fieldsMap.keys.forall(_.getContainingType() == descriptor), "FieldDescriptor does not match message type.")
    val __fields = descriptor.getFields
    com.winry.mahjong.message.ReadyResp(
    )
  }
  def descriptor: _root_.com.google.protobuf.Descriptors.Descriptor = MessageProto.descriptor.getMessageTypes.get(4)
  def messageCompanionForField(__field: _root_.com.google.protobuf.Descriptors.FieldDescriptor): _root_.com.trueaccord.scalapb.GeneratedMessageCompanion[_] = throw new MatchError(__field)
  def enumCompanionForField(__field: _root_.com.google.protobuf.Descriptors.FieldDescriptor): _root_.com.trueaccord.scalapb.GeneratedEnumCompanion[_] = throw new MatchError(__field)
  lazy val defaultInstance = com.winry.mahjong.message.ReadyResp(
  )
  implicit class ReadyRespLens[UpperPB](_l: _root_.com.trueaccord.lenses.Lens[UpperPB, com.winry.mahjong.message.ReadyResp]) extends _root_.com.trueaccord.lenses.ObjectLens[UpperPB, com.winry.mahjong.message.ReadyResp](_l) {
  }
}