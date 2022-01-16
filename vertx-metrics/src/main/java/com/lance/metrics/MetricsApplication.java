package com.lance.metrics;

import com.lance.metrics.prop.ConfigProperties;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.micrometer.MicrometerMetricsOptions;
import io.vertx.micrometer.VertxPrometheusOptions;

/**
 * Metrics application
 *
 * @author lance
 * @date 2022/1/16 10:05
 */
public class MetricsApplication {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    ConfigRetriever retriever = readYaml(vertx);

    retriever.getConfig(json -> {
      JsonObject object = json.result();
      //init metrics
      initMetric(object.getJsonObject("metrics"));
      DeploymentOptions options = new DeploymentOptions().setConfig(object);
      vertx.deployVerticle(MainApp.class.getName(), options);
    });
  }

  /**
   * init metrics
   */
  private static void initMetric(JsonObject object) {
    ConfigProperties.MetricsProperties prop = object.mapTo(ConfigProperties.MetricsProperties.class);

    Vertx.vertx(new VertxOptions().setMetricsOptions(
        new MicrometerMetricsOptions()
            .setPrometheusOptions(new VertxPrometheusOptions().setEnabled(true)
                .setStartEmbeddedServer(true)
                .setEmbeddedServerOptions(new HttpServerOptions().setPort(prop.getPort()))
                .setEmbeddedServerEndpoint(prop.getEndpoint()))
            .setEnabled(prop.isEnabled())));
  }

  /**
   * read yaml
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
