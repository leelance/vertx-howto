package com.lance.config;

import io.vertx.core.Vertx;

/**
 * config application
 *
 * @author lance
 * @date 2022/1/5 0:04
 */
public class ConfigApplication {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(MainApp.class.getName());
	}

}
