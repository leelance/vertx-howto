package com.lance.route.web;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

/**
 * user router handler
 *
 * @author lance
 * @date 2021/12/29 10:31
 */
public class UserRoute {

  public Router create(Vertx vertx) {
    Router userRouter = Router.router(vertx);

    userRouter.get("/list").handler(ctx -> {
      ctx.response()
          .putHeader("content-type", "text/plain")
          .end("user list");
    });

    userRouter.post("/add").handler(ctx -> {
      ctx.response()
          .putHeader("content-type", "text/plain")
          .end("user add");
    });
    return userRouter;
  }
}
