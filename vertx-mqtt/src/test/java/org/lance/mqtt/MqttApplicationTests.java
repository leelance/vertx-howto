package org.lance.mqtt;

import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.mqtt.MqttClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
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
class MqttApplicationTests {
  MqttClient client = null;

  @BeforeEach
  void init(Vertx vertx) {
    client = MqttClient.create(vertx);
    client.connect(18003, "127.0.0.1", s -> {
      client.disconnect();
    });
  }

  @Test
  void clientConnect() throws InterruptedException {
    client.publishHandler(s -> {
              System.out.println("There are new message in topic: " + s.topicName());
              System.out.println("Content(as string) of the message: " + s.payload().toString());
              System.out.println("QoS: " + s.qosLevel());
            }
        )
        .subscribe("rpi2/temp", 2);

    Thread.sleep(60_000L);
  }
}
