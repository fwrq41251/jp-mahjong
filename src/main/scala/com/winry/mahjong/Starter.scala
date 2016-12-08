package com.winry.mahjong

import java.net.InetAddress

import akka.actor.{ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import com.winry.mahjong.actor.{Lobby, Server}
import org.apache.zookeeper.ZooDefs.Ids
import org.apache.zookeeper.{CreateMode, WatchedEvent, Watcher, ZooKeeper}

/**
  * Created by User on 11/25/2016.
  */
object Starter extends App {

  val env = args(0)
  val config = ConfigFactory.load()

  val actorSystem = ActorSystem("server")
  val serverConfig = config.getConfig("server").getConfig(env)
  val host = serverConfig.getString("host")
  val port = serverConfig.getInt("port")
  val server = actorSystem.actorOf(Server.props(host, port), "front")
  val lobby = actorSystem.actorOf(Props[Lobby], "lobby")

  class ZookeeperWatcher extends Watcher {

    override def process(event: WatchedEvent): Unit = {

    }
  }

  val zkConfig = config.getConfig("zookeeper").getConfig(env)
  val connectString = zkConfig.getString("host-port")
  val path = zkConfig.getString("path")
  val zk = new ZooKeeper(connectString, 3000, new ZookeeperWatcher)
  zk.create(path, (InetAddress.getLocalHost + ":" + port).getBytes("utf-8"), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL)
}
