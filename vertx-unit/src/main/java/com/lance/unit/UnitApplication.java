package com.lance.unit;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * unit application
 *
 * @author lance
 * @date 2022/1/20 11:37
 */
public class UnitApplication {

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
