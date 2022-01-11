package com.lance.redis.prop;

import io.vertx.redis.client.RedisClientType;
import io.vertx.redis.client.RedisRole;
import lombok.Data;

/**
 * config properties
 *
 * @author lance
 * @date 2022/1/6 0:00
 */
@Data
public class ConfigProperties {
  /**
   * mysql config
   */
  private RedisProperties redis;
  /**
   * server config
   */
  private ServerProperties server;

  @Data
  public static class RedisProperties {
    private RedisClientType clientType = RedisClientType.STANDALONE;
    private String[] urls;
    private String password;

    private String poolName = "redis-p";
    private int poolCleanerInterval = 30_000;
    private int maxPoolSize = 8;
    private int maxPoolWaiting = 32;
    private String masterName = "mymaster";
    private RedisRole role = RedisRole.MASTER;
  }

  @Data
  public static class ServerProperties {
    private int port;
  }
}
