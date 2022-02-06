package org.lance.multi;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

/**
 * multi vertical application
 *
 * @author lance
 * @date 2022/1/26 17:58
 */
public class MultiVerticalApplication extends AbstractVerticle {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(MultiVerticalApplication.class.getName());
  }

  @Override
  public void start() {
    ConfigRetriever retriever = readYaml(vertx);

    retriever.getConfig(json -> {
      JsonObject object = json.result();

      DeploymentOptions options = new DeploymentOptions().setConfig(object);

      Router router = Router.router(vertx);
      vertx.deployVerticle(new HelloVertical(router), options);
      vertx.deployVerticle(new WorldVertical(router), options);
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
