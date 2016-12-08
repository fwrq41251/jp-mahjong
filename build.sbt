name := "jp-mahjong"

version := "1.0.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "ch.qos.logback" % "logback-core" % "1.1.7",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.14",
  "com.typesafe.akka" % "akka-slf4j_2.11" % "2.4.14",
  "com.trueaccord.scalapb" % "scalapb-runtime_2.11" % "0.5.45",
  "org.apache.zookeeper" % "zookeeper" % "3.4.9" exclude("org.slf4j", "slf4j-log4j12"),
  "com.google.guava" % "guava" % "20.0",
  "junit" % "junit" % "4.12" % Test
)
