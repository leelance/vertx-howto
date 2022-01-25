package org.lance.mqtt;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttEndpoint;
import io.vertx.mqtt.MqttServer;
import io.vertx.mqtt.MqttServerOptions;
import lombok.extern.slf4j.Slf4j;
import org.lance.mqtt.prop.ConfigProperties;

import java.nio.charset.Charset;

/**
 * mqtt vertical
 *
 * @author lance
 * @date 2022/1/21 20:09
 */
@Slf4j
public class MqttServerApp extends AbstractVerticle {
  private final static String CLIENT_ID = "clientHello";

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    ConfigProperties properties = config().mapTo(ConfigProperties.class);
    int port = properties.getServer().getPort();
    log.info("===>json: {}, port: {}", properties, port);

    MqttServer mqttServer = MqttServer.create(vertx, create(properties));
    mqttServer.endpointHandler(endpoint -> {
          // shows main connect info
          log.info("MQTT client [{}] request to connect, clean session = {}", endpoint.clientIdentifier(), endpoint.isCleanSession());
          if (endpoint.auth() != null) {
            log.info("[username = {}, password = {}]", endpoint.auth().getUsername(), endpoint.auth().getPassword());
          }

          log.info("[properties = {}]", endpoint.connectProperties());
          if (endpoint.will() != null) {
            log.info("[will topic: {}, msg: {}, QoS: {}, isRetain: {}]", endpoint.will().getWillTopic(), endpoint.will().getWillMessageBytes(), endpoint.will().getWillQos(), endpoint.will().isWillRetain());
          }

          log.info("[keep alive timeout = {}]", endpoint.keepAliveTimeSeconds());
          // accept connection from the remote client
          endpoint.accept(true);
          receiver(endpoint);
          endpoint.disconnectMessageHandler(disconnectMessage -> log.info("Received disconnect from client, reason code = {}", disconnectMessage.code()));
        })
        .exceptionHandler(t -> log.error("MQTT exception fail: ", t))
        .listen(ar -> {
          if (ar.succeeded()) {
            log.warn("MQTT server is listening on port: {}", ar.result().actualPort());
          } else {
            log.error("Fail on starting the server: ", ar.cause());
          }
        });

  }

  private void receiver(MqttEndpoint endpoint) {
    endpoint.publishHandler(p -> {
          log.info("Server received message [{}] with QoS [{}]", p.payload().toString(Charset.defaultCharset()), p.qosLevel());
          if (p.qosLevel() == MqttQoS.AT_LEAST_ONCE) {
            endpoint.publishAcknowledge(p.messageId());
          } else if (p.qosLevel() == MqttQoS.EXACTLY_ONCE) {
            endpoint.publishReceived(p.messageId());
          }
          send(endpoint);
        })
        .publishReleaseHandler(endpoint::publishComplete);
  }

  private void send(MqttEndpoint endpoint) {
    Buffer payload = Buffer.buffer("server: hello world.");
    endpoint.publish(MqttClientApp.MQTT_TOPIC, payload, MqttQoS.AT_MOST_ONCE, false, false, s -> {
      if (s.succeeded()) {
        log.info("===>Server publish success: {}", s.result());
      } else {
        log.error("===>Server publish fail: ", s.cause());
      }
    });
  }

  private MqttServerOptions create(ConfigProperties configProperties) {
    MqttServerOptions options = new MqttServerOptions();
    options.setPort(configProperties.getServer().getPort());
    options.setHost(configProperties.getServer().getHost());
    return options;
  }
}
