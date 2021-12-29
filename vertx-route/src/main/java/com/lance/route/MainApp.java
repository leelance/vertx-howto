package com.lance.route;

import com.lance.route.web.MainRoute;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;

/**
 * Main
 *
 * @author lance
 * @date 2021/12/12 23:30
 */
public class MainApp extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();
    server.requestHandler(new MainRoute().create(vertx));

    server.listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
