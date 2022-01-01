package com.lance.logging;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * Main
 *
 * @author lance
 * @date 2021/12/12 23:30
 */
@Slf4j
public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.createHttpServer().requestHandler(req -> {
          IntStream.range(0, 100).forEach(i -> log.info("===>start print i: {}, time: {}", i, System.nanoTime()));
          req.response().putHeader("content-type", "text/plain").end("Hello from Vert.x!");
        }
    ).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        log.info("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
