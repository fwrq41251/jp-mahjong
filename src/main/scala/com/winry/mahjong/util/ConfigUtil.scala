package com.winry.mahjong.util

import com.typesafe.config.{Config, ConfigFactory}

/**
  * Created by User on 12/9/2016.
  */
object ConfigUtil {

  var env: String = "dev"
  val config: Config = ConfigFactory.load()

  case class ServerConfig(host: String, port: Int)

  case class ZookeeperConfig(register: Boolean, hostPort: String, path: String)

  case class RedisConfig(host: String, port: Int)

  def getServerConfig: ServerConfig = {
    val serverConfig = config.getConfig("server").getConfig(env)
    ServerConfig(serverConfig.getString("host"), serverConfig.getInt("port"))
  }

  def getZookeeperConfig: ZookeeperConfig = {
    val zkConfig = config.getConfig("zookeeper").getConfig(env)
    ZookeeperConfig(zkConfig.getBoolean("register"), zkConfig.getString("host-port"), zkConfig.getString("path"))
  }

  def getRedisConfig: RedisConfig = {
    val redisConfig = config.getConfig("redis").getConfig(env)
    RedisConfig(redisConfig.getString("host"), redisConfig.getInt("port"))
  }

}
