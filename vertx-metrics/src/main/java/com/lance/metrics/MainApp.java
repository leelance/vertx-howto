package com.lance.metrics;

import com.lance.metrics.prop.ConfigProperties;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * main app
 *
 * @author lance
 * @date 2022/1/16 10:23
 */
@Slf4j
public class MainApp extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();
    ConfigProperties properties = config().mapTo(ConfigProperties.class);
    int port = properties.getServer().getPort();
    log.info("===>json: {}, port: {}", properties, port);

    server.requestHandler(req -> {
          IntStream.range(0, 10).forEach(i -> log.info("===>start print i: {}, time: {}", i, System.nanoTime()));
          req.response().putHeader("content-type", "text/plain").end("Hello metric.");
        }
    ).listen(port, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        log.info("HTTP server started on port " + port);
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
