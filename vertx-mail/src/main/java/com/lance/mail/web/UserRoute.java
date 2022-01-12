package com.lance.mail.web;

import com.lance.mail.service.UserService;
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

		userRouter.post("/send/message").handler(service::sendMessage);
		return userRouter;
	}
}
