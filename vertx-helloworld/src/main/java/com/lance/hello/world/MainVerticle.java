package com.lance.hello.world;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

/**
 * Main
 *
 * @author lance
 * @date 2021/12/12 23:30
 */
public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
          .putHeader("content-type", "text/plain")
          .end("Hello from Vert.x!");
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
