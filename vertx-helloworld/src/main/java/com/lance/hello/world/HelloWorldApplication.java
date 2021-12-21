package com.lance.hello.world;

import io.vertx.core.Vertx;

/**
 * 简单测试
 *
 * @author lance
 * @date 2021/12/21 10:56
 */
public class HelloWorldApplication {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(MainVerticle.class.getName());
  }
}
