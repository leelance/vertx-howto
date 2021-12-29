package com.lance.route.web;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;

/**
 * main router
 *
 * @author lance
 * @date 2021/12/29 10:29
 */
public class MainRoute {
  public Router create(Vertx vertx) {
    Router mainRouter = Router.router(vertx);
    mainRouter.mountSubRouter("/user", new UserRoute().create(vertx));
    mainRouter.mountSubRouter("/book", new BookRoute().create(vertx));
    return mainRouter;
  }
}
