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
    ConfigRetriever retriever = readYaml(vertx);

    retriever.getConfig(json -> {
      DeploymentOptions options = new DeploymentOptions().setConfig(json.result());
      vertx.deployVerticle(MainApp.class.getName(), options);
    });
  }

  private static ConfigRetriever readYaml(Vertx vertx) {
    ConfigStoreOptions store = new ConfigStoreOptions()
        .setType("file")
        .setFormat("yaml")
        .setOptional(true)
        .setConfig(new JsonObject().put("path", "application.yaml"));

    return ConfigRetriever.create(vertx, new ConfigRetrieverOptions().addStore(store));
  }
}
