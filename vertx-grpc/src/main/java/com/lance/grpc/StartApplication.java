package com.lance.grpc;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * vertx start application
 *
 * @author lance
 * @date 2022/2/13 18:44
 */
@Slf4j
public class StartApplication {
  public static void runClustered(Class<? extends AbstractVerticle> clazz) {
    run(clazz, new VertxOptions(), null, true);
  }

  public static void run(Class<? extends AbstractVerticle> clazz) {
    run(clazz, new VertxOptions(), null, false);
  }

  public static void run(Class<? extends AbstractVerticle> clazz, DeploymentOptions options) {
    run(clazz, new VertxOptions(), options, false);
  }

  public static void run(Class<? extends AbstractVerticle> clazz, VertxOptions options, DeploymentOptions deploymentOptions, boolean clustered) {
    run(clazz.getName(), options, deploymentOptions, clustered);
  }

  public static void run(String className, VertxOptions options, DeploymentOptions deploymentOptions, boolean clustered) {
    if (options == null) {
      // Default parameter
      options = new VertxOptions();
    }

    Consumer<Vertx> runner = vertx -> {
      try {
        if (deploymentOptions != null) {
          vertx.deployVerticle(className, deploymentOptions);
        } else {
          vertx.deployVerticle(className);
        }
      } catch (Throwable t) {
        log.error("Vertx deploy fail: ", t);
      }
    };

    if (clustered) {
      Vertx.clusteredVertx(options, res -> {
        if (res.succeeded()) {
          Vertx vertx = res.result();
          runner.accept(vertx);
        } else {
          res.cause().printStackTrace();
        }
      });
    } else {
      Vertx vertx = Vertx.vertx(options);
      runner.accept(vertx);
    }
  }
}
