package com.lance.mysql.config;

import io.vertx.mysqlclient.MySQLPool;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * db helper
 *
 * @author lance
 * @date 2022/1/8 20:05
 */
@Data
@Slf4j
public class DbHelper {
  private MySQLPool mySqlPool;

  public String get() {
    log.info("===>db helper print...");
    return "ok";
  }
}
