package com.lance.exception;

import io.vertx.core.Vertx;

/**
 * Exception application
 *
 * @author lance
 * @date 2022/1/4 20:52
 */
public class ExceptionApplication {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(MainApp.class.getName());
	}
}
