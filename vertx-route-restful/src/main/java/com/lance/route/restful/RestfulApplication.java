package com.lance.route.restful;

import io.vertx.core.Vertx;

/**
 * restful
 *
 * @author lance
 * @date 2021/12/31 15:26
 */
public class RestfulApplication {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(MainApp.class.getName());
  }
}
