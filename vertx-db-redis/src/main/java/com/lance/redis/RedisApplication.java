package com.lance.redis;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import lombok.extern.slf4j.Slf4j;

/**
 * redis application
 *
 * @author lance
 * @date 2022/1/11 18:26
 */
@Slf4j
public class RedisApplication {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    ConfigRetriever retriever = readYaml(vertx);

    retriever.getConfig(json -> {
      try {
        JsonObject object = json.result();

        DeploymentOptions options = new DeploymentOptions().setConfig(object);
        vertx.deployVerticle(MainApp.class.getName(), options);
      } catch (Exception ex) {
        log.error("===> Vertx start fail: ", ex);
      }
    });
  }

  /**
   * read yaml config
   *
   * @param vertx vertx
   * @return ConfigRetriever
   */
  private static ConfigRetriever readYaml(Vertx vertx) {
    ConfigStoreOptions store = new ConfigStoreOptions()
        .setType("file")
        .setFormat("yaml")
        .setOptional(true)
        .setConfig(new JsonObject().put("path", "application.yaml"));

    return ConfigRetriever.create(vertx, new ConfigRetrieverOptions().addStore(store));
  }

}
