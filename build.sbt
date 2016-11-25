name := "jp-mahjong"

version := "1.0.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "ch.qos.logback" % "logback-core" % "1.1.7",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.12",
  "com.typesafe.akka" % "akka-slf4j_2.11" % "2.4.12",
  "com.google.protobuf" % "protobuf-java" % "3.1.0"
)

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)