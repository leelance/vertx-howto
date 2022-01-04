package com.lance.config;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * config application
 *
 * @author lance
 * @date 2022/1/5 0:04
 */
public class ConfigApplication {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		JsonObject object = readYaml(vertx);
		vertx.deployVerticle(MainApp.class.getName(), new DeploymentOptions().setConfig(object));
	}

	private static JsonObject readYaml(Vertx vertx) {
		ConfigStoreOptions store = new ConfigStoreOptions()
				.setType("file")
				.setFormat("yaml")
				.setConfig(new JsonObject().put("path", "application.yaml")
				);

		ConfigRetriever retriever = ConfigRetriever.create(vertx, new ConfigRetrieverOptions().addStore(store));
		return retriever.getConfig().result();
	}
}
