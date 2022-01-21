package org.lance.mqtt;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.mqtt.MqttServer;
import io.vertx.mqtt.MqttServerOptions;
import lombok.extern.slf4j.Slf4j;
import org.lance.mqtt.prop.ConfigProperties;

/**
 * mqtt vertical
 *
 * @author lance
 * @date 2022/1/21 20:09
 */
@Slf4j
public class MainApp extends AbstractVerticle {

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
            System.out.println("[will topic = " + endpoint.will().getWillTopic() + " msg = " + new String(endpoint.will().getWillMessageBytes()) +
                " QoS = " + endpoint.will().getWillQos() + " isRetain = " + endpoint.will().isWillRetain() + "]");
          }

          log.info("[keep alive timeout = {}]", endpoint.keepAliveTimeSeconds());
          // accept connection from the remote client
          endpoint.accept(false);
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

  private MqttServerOptions create(ConfigProperties configProperties) {
    MqttServerOptions options = new MqttServerOptions();
    options.setPort(configProperties.getServer().getPort());

    return options;
  }
}
