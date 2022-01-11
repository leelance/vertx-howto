package com.lance.redis.config;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
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

  /**
   * 初始化mysql连接
   */
  public void afterPropertiesSet() {

  }
}
