package com.lance.mail.web;

import com.lance.config.handler.DefaultExceptionHandler;
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
		mainRouter.route().consumes("application/json; charset=utf-8");
		mainRouter.route().produces("application/json; charset=utf-8");
		mainRouter.route().handler(BodyHandler.create());

		mainRouter.mountSubRouter("/user", new UserRoute().create(vertx));

		//统一异常处理拦截
		mainRouter.route().last().failureHandler(DefaultExceptionHandler.of());
		return mainRouter;
	}
}
