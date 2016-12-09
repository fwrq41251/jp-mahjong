package com.winry.mahjong.util

import java.net.InetAddress

import com.typesafe.config.ConfigFactory
import org.apache.zookeeper.ZooDefs.Ids
import org.apache.zookeeper.{CreateMode, WatchedEvent, Watcher, ZooKeeper}

/**
  * Created by User on 12/8/2016.
  */
object ServerRegister {

  def register(env: String, port: Int): Unit = {
    class ZookeeperWatcher extends Watcher {

      override def process(event: WatchedEvent): Unit = {

      }
    }

    val config = ConfigFactory.load()
    val zkConfig = config.getConfig("zookeeper").getConfig(env)
    if (zkConfig.getBoolean("register")) {
      val connectString = zkConfig.getString("host-port")
      val path = zkConfig.getString("path")
      val zk = new ZooKeeper(connectString, 3000, new ZookeeperWatcher)
      zk.exists(path, false) match {
        case null => zk.create(path, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
        case _ =>
      }
      zk.create(path + "/" + "server", (InetAddress.getLocalHost.getHostAddress + ":" + port).getBytes("utf-8"), Ids
        .OPEN_ACL_UNSAFE,
        CreateMode.EPHEMERAL_SEQUENTIAL)
    }
  }

}
