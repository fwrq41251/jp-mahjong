package com.winry.mahjong.util

import java.net.InetAddress

import org.apache.zookeeper.ZooDefs.Ids
import org.apache.zookeeper.{CreateMode, WatchedEvent, Watcher, ZooKeeper}

/**
  * Created by User on 12/8/2016.
  */
object ServerRegister {

  val hostPort = InetAddress.getLocalHost.getHostAddress

  def register(port: Int): Unit = {
    class ZookeeperWatcher extends Watcher {

      override def process(event: WatchedEvent): Unit = {

      }
    }

    val zkConfig = ConfigUtil.getZookeeperConfig
    if (zkConfig.register) {
      val zk = new ZooKeeper(zkConfig.hostPort, 3000, new ZookeeperWatcher)
      val path = zkConfig.path
      zk.exists(path, false) match {
        case null => zk.create(path, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT)
        case _ =>
      }
      zk.create(path + "/" + "server", (hostPort + ":" + port).getBytes("utf-8"), Ids
        .OPEN_ACL_UNSAFE,
        CreateMode.EPHEMERAL_SEQUENTIAL)
    }
  }

}
