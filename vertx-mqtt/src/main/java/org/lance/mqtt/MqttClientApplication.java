package org.lance.mqtt;

import io.vertx.core.Vertx;

/**
 * MQTT application
 *
 * @author lance
 * @date 2022/1/21 19:20
 */
public class MqttClientApplication {

  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(MqttClientApp.class.getName());
  }

}
