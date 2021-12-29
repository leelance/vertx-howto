package com.lance.route.web;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * user router handler
 *
 * @author lance
 * @date 2021/12/29 10:31
 */
public class UserRoute {
  private final static Logger log = LoggerFactory.getLogger(UserRoute.class);

  public Router create(Vertx vertx) {
    log.info("=====>Init user router.");
    Router userRouter = Router.router(vertx);

    userRouter.get("/list").handler(ctx -> {
      ctx.response()
          .putHeader("content-type", "text/plain")
          .end("user list");
    });

    userRouter.post("/add").handler(ctx -> {
      log.info("hello: {}", ctx.request().headers());
      ctx.response()
          .putHeader("content-type", "text/plain")
          .end("user add");
    });
    return userRouter;
  }
}
