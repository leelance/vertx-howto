package com.lance.logging;

import io.vertx.core.Vertx;

/**
 * log applicaton
 *
 * @author lance
 * @date 2021/12/31 23:26
 */
public class LogApplication {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(MainVerticle.class.getName());
  }
}
