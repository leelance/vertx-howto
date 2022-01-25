package org.lance.mqtt;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttClient;
import io.vertx.mqtt.MqttClientOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.Charset;

/**
 * mqtt client app
 *
 * @author lance
 * @date 2022/1/25 17:35
 */
@Slf4j
public class MqttClientApp extends AbstractVerticle {
  public static final String MQTT_TOPIC = "hello_topic";

  @Override
  public void start() {
    MqttClient client = MqttClient.create(vertx, create());

    // handler will be called when we have a message in topic we subscribe for
    client.publishHandler(p -> {
      log.info("Client received message on [{}] payload [{}] with QoS [{}]", p.topicName(), p.payload().toString(Charset.defaultCharset()), p.qosLevel());
    });

    client.connect(18003, "127.0.0.1", s -> {
      if (s.succeeded()) {
        log.info("Client connect success.");
        subscribe(client);
      } else {
        log.error("Client connect fail: ", s.cause());
      }
    }).exceptionHandler(event -> {
      log.error("client fail: ", event.getCause());
    });
  }

  private void subscribe(MqttClient client) {
    client.subscribe(MQTT_TOPIC, 0, e -> {
      if (e.succeeded()) {
        log.info("===>subscribe success: {}", e.result());
        vertx.setPeriodic(10_000, l -> publish(client));
      } else {
        log.error("===>subscribe fail: ", e.cause());
      }
    });
  }

  private void publish(MqttClient client) {
    Buffer payload = Buffer.buffer("client: hello world.");
    client.publish(MQTT_TOPIC, payload, MqttQoS.AT_MOST_ONCE, false, false, s -> {
      if (s.succeeded()) {
        log.info("===>Client publish success: {}", s.result());
      } else {
        log.error("===>Client publish fail: ", s.cause());
      }
    });
  }

  private MqttClientOptions create() {
    MqttClientOptions options = new MqttClientOptions();
    options.setClientId("ClientId_" + RandomStringUtils.randomAlphanumeric(6));
    options.setMaxMessageSize(100_000_000);
    options.setKeepAliveInterval(2);
    return options;
  }
}
