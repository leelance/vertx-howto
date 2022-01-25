package org.lance.mqtt;

import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.mqtt.MqttClient;
import io.vertx.mqtt.MqttClientOptions;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * mqtt test
 *
 * @author lance
 * @date 2022/1/21 20:38
 */
@Slf4j
@ExtendWith(VertxExtension.class)
class MqttServerApplicationTests {
  Vertx vertx = Vertx.vertx();
  private final static String CLIENT_ID = "clientHello";

  @Test
  void clientConnect() throws InterruptedException {
    MqttClient client = MqttClient.create(vertx, create());
    client.connect(18003, "127.0.0.1", s -> {
      if (s.succeeded()) {
        log.info("Client connect success.");
      } else {
        log.error("Client connect fail: ", s.cause());
      }
    }).exceptionHandler(event -> log.error("client fail: ", event.getCause()));

    client.subscribe("hello_topic", 0, e -> {
      if (e.succeeded()) {
        log.info("===>subscribe success: {}", e.result());
      } else {
        log.error("===>subscribe fail: ", e.cause());
      }
    });

  }

  private MqttClientOptions create() {
    MqttClientOptions options = new MqttClientOptions();
    options.setClientId(CLIENT_ID);
    return options;
  }
}
