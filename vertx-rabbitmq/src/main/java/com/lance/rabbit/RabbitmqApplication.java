package com.lance.rabbit;

import com.lance.rabbit.config.ClientHelper;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

/**
 * Rabbitmq application
 *
 * @author lance
 * @date 2022/2/13 12:26
 */
public class RabbitmqApplication {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    ConfigRetriever retriever = readYaml(vertx);

    retriever.getConfig(json -> {
      JsonObject object = json.result();
      ClientHelper dbHelper = new ClientHelper(object.getJsonObject("rabbit"), vertx);
      dbHelper.afterPropertiesSet();

      DeploymentOptions options = new DeploymentOptions().setConfig(object);
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
