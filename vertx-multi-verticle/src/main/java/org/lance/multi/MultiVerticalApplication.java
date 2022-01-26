package org.lance.multi;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
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
    Router router = Router.router(vertx);
    vertx.deployVerticle(new HelloVertical(router));
    vertx.deployVerticle(new WorldVertical(router));
  }
}
