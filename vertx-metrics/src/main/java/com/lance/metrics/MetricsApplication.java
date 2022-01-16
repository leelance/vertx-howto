package com.lance.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonObject;
import io.vertx.micrometer.Label;
import io.vertx.micrometer.MetricsNaming;
import io.vertx.micrometer.MicrometerMetricsOptions;
import io.vertx.micrometer.VertxPrometheusOptions;
import io.vertx.micrometer.backends.BackendRegistries;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Metrics application
 *
 * @author lance
 * @date 2022/1/16 10:05
 */
public class MetricsApplication {

  public static void main(String[] args) {
    VertxOptions vertxOptions = new VertxOptions();
    //init metrics
    initMetric(vertxOptions);

    Vertx vertx = Vertx.vertx(vertxOptions);
    ConfigRetriever retriever = readYaml(vertx);

    retriever.getConfig(json -> {
      JsonObject object = json.result();
      DeploymentOptions options = new DeploymentOptions().setConfig(object);
      vertx.deployVerticle(MainApp.class.getName(), options);
    });
  }

  /**
   * init metrics
   */
  private static void initMetric(VertxOptions vertxOptions) {
    MeterRegistry registry = BackendRegistries.getDefaultNow();
    vertxOptions.setMetricsOptions(
        new MicrometerMetricsOptions()
            .setMetricsNaming(MetricsNaming.v4Names())
            .setLabels(Arrays.stream(Label.values()).collect(Collectors.toSet()))
            .setPrometheusOptions(new VertxPrometheusOptions().setEnabled(true)
                .setStartEmbeddedServer(true)
                .setEmbeddedServerOptions(new HttpServerOptions().setPort(18001))
                .setEmbeddedServerEndpoint("/metrics/exporter"))
            .setRegistryName("vertx-metric")
            .setMicrometerRegistry(registry)
            .setEnabled(true));
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
