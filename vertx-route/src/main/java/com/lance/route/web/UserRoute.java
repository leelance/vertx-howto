package com.lance.route.web;

import com.lance.route.service.UserService;
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
    UserService service = new UserService();

    userRouter.get("/list").handler(service::list);
    userRouter.post("/add").handler(service::add);
    return userRouter;
  }
}
