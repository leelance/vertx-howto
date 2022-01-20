package com.lance.unit.web;

import com.lance.unit.service.UserService;
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
    UserService service = new UserService();

    userRouter.get("/list").handler(service::list);
    userRouter.post("/info").handler(service::add);
    userRouter.put("/info").handler(service::update);
    userRouter.delete("/info/:userId").handler(service::delete);
    userRouter.get("/info/:userId").handler(service::detail);
    return userRouter;
  }
}
