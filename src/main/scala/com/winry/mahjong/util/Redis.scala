package com.winry.mahjong.util

import redis.clients.jedis.{Jedis, JedisPool}

/**
  * Created by User on 12/9/2016.
  */
object Redis {

  val pool: JedisPool = {
    val config = ConfigUtil.getRedisConfig
    new JedisPool(config.host, config.port)
  }

  def getResource: Jedis = {
    pool.getResource
  }
}
