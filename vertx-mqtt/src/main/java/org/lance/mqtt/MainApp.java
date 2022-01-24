package org.lance.mqtt;

import io.netty.handler.codec.mqtt.MqttQoS;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.mqtt.MqttEndpoint;
import io.vertx.mqtt.MqttServer;
import io.vertx.mqtt.MqttServerOptions;
import io.vertx.mqtt.MqttTopicSubscription;
import lombok.extern.slf4j.Slf4j;
import org.lance.mqtt.prop.ConfigProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * mqtt vertical
 *
 * @author lance
 * @date 2022/1/21 20:09
 */
@Slf4j
public class MainApp extends AbstractVerticle {
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
            log.info("[will topic: {}, msg: {}, QoS: {}, isRetain: {}]", endpoint.will().getWillTopic(), new String(endpoint.will().getWillMessageBytes()), endpoint.will().getWillQos(), endpoint.will().isWillRetain());
          }

          log.info("[keep alive timeout = {}]", endpoint.keepAliveTimeSeconds());
          // accept connection from the remote client
          endpoint.accept(false);

          if (CLIENT_ID.equals(endpoint.clientIdentifier())) {
            doPublish(endpoint);
            handleClientDisconnect(endpoint);
          } else {
            handleSubscription(endpoint);
            handleUnsubscription(endpoint);
            handleClientDisconnect(endpoint);
          }

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

  protected void handleSubscription(MqttEndpoint endpoint) {
    endpoint.subscribeHandler(subscribe -> {
      List<MqttQoS> grantedQosLevels = new ArrayList<>();
      for (MqttTopicSubscription s : subscribe.topicSubscriptions()) {
        log.info("Subscription for {} with QoS {}", s.topicName(), s.qualityOfService());
        grantedQosLevels.add(s.qualityOfService());
      }
      endpoint.subscribeAcknowledge(subscribe.messageId(), grantedQosLevels);
    });
  }

  private void handleUnsubscription(MqttEndpoint endpoint) {
    endpoint.unsubscribeHandler(unsubscribe -> {
      for (String t : unsubscribe.topics()) {
        log.info("Unsubscription for {}", t);
      }
      endpoint.unsubscribeAcknowledge(unsubscribe.messageId());
    });
  }

  private void publishHandler(MqttEndpoint endpoint) {
    endpoint.publishHandler(message -> {
      endpoint.publishAcknowledge(message.messageId());
    }).publishReleaseHandler(endpoint::publishComplete);
  }

  private void handleClientDisconnect(MqttEndpoint endpoint) {
    endpoint.disconnectHandler(h -> {
      log.info("The remote client has closed the connection.");
    });
  }

  private void doPublish(MqttEndpoint endpoint) {
    // just as example, publish a message with QoS level 2
    endpoint.publish("topic_demo",
        Buffer.buffer("Hello from the Vert.x MQTT server"),
        MqttQoS.EXACTLY_ONCE,
        false,
        false);
    publishHandler(endpoint);
    // specifing handlers for handling QoS 1 and 2
    endpoint.publishAcknowledgeHandler(messageId -> {
          log.info("Received ack for message = {}", messageId);
        })
        .publishReceivedHandler(endpoint::publishRelease)
        .publishCompletionMessageHandler(messageId -> {
          log.info("Received ack for message = {}", messageId);
        });
  }

  private MqttServerOptions create(ConfigProperties configProperties) {
    MqttServerOptions options = new MqttServerOptions();
    options.setPort(configProperties.getServer().getPort());
    options.setHost(configProperties.getServer().getHost());
    return options;
  }
}
