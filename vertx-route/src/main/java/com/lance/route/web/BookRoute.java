package com.lance.route.web;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

/**
 * book router
 *
 * @author lance
 * @date 2021/12/29 10:45
 */
public class BookRoute {
  public Router create(Vertx vertx) {
    Router bookRouter = Router.router(vertx);

    bookRouter.get("/list").handler(ctx -> {
      ctx.response()
          .putHeader("content-type", "text/plain")
          .end("book list");
    });

    bookRouter.post("/add").handler(ctx -> {
      ctx.response()
          .putHeader("content-type", "text/plain")
          .end("book add");
    });
    return bookRouter;
  }
}
