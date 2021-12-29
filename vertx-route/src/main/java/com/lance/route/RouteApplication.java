package com.lance.route;

import io.vertx.core.Vertx;

/**
 * Route application
 *
 * @author lance
 * @date 2021/12/24 22:01
 */
public class RouteApplication {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(MainApp.class.getName());
  }
}
