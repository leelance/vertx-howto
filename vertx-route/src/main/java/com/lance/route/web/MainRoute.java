package com.lance.route.web;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * main router
 *
 * @author lance
 * @date 2021/12/29 10:29
 */
public class MainRoute {
  public Router create(Vertx vertx) {
    Router mainRouter = Router.router(vertx);
    mainRouter.route().consumes("application/json");
    mainRouter.route().produces("application/json");
    mainRouter.route().handler(BodyHandler.create());

    mainRouter.mountSubRouter("/user", new UserRoute().create(vertx));
    mainRouter.mountSubRouter("/book", new BookRoute().create(vertx));
    return mainRouter;
  }
}
