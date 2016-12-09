package com.winry.mahjong.util

import redis.clients.jedis.Jedis

/**
  * Created by User on 12/9/2016.
  */
object Redis {

  val client: Jedis = {
    val config = ConfigUtil.getRedisConfig
    new Jedis(config.host, config.port)
  }

  def setSessionCount(count: Int): Unit = {
    client.set("server:" + ServerRegister.hostPort, count.toString)
  }
}
