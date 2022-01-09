package com.lance.mysql.config;

import com.lance.mysql.prop.ConfigProperties;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.mysqlclient.MySQLConnectOptions;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.PoolOptions;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * db helper
 *
 * @author lance
 * @date 2022/1/8 20:05
 */
@Data
@Slf4j
@RequiredArgsConstructor
public class DbHelper {
  private final JsonObject object;
  private final Vertx vertx;
  private static MySQLPool mySqlPool;

  /**
   * 获取客户端
   *
   * @return MySQLPool
   */
  public static MySQLPool client() {
    return mySqlPool;
  }

  /**
   * 初始化mysql连接
   */
  public void afterPropertiesSet() {
    ConfigProperties.MysqlProperties properties = object.mapTo(ConfigProperties.MysqlProperties.class);
    MySQLConnectOptions connectOptions = new MySQLConnectOptions()
        .setPort(properties.getPort())
        .setHost(properties.getHost())
        .setDatabase(properties.getDatabase())
        .setUser(properties.getUsername())
        .setPassword(properties.getPassword())
        .setReconnectAttempts(properties.getReconnectAttempts())
        .setReconnectInterval(properties.getReconnectInterval())
        .setCharset(properties.getCharset())
        .setCollation(properties.getCollation());

    PoolOptions poolOptions = new PoolOptions()
        .setMaxSize(properties.getMaxSize())
        .setName(properties.getPoolName())
        .setShared(true);

    mySqlPool = MySQLPool.pool(vertx, connectOptions, poolOptions);
  }
}
