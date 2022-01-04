package com.lance.exception;

import com.lance.exception.web.MainRoute;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import lombok.extern.slf4j.Slf4j;

/**
 * Main
 *
 * @author lance
 * @date 2021/12/12 23:30
 */
@Slf4j
public class MainApp extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		HttpServer server = vertx.createHttpServer();
		server.requestHandler(new MainRoute().create(vertx));

		server.listen(8888, http -> {
			if (http.succeeded()) {
				startPromise.complete();
				log.info("HTTP server started on port 8888");
			} else {
				startPromise.fail(http.cause());
			}
		});
	}
}
