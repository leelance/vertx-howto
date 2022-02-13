package com.lance.rabbit.config;

import com.lance.rabbit.prop.ConfigProperties;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.rabbitmq.RabbitMQClient;
import io.vertx.rabbitmq.RabbitMQConsumer;
import io.vertx.rabbitmq.RabbitMQOptions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * rabbit mq client
 *
 * @author lance
 * @date 2022/2/13 12:48
 */
@Slf4j
@RequiredArgsConstructor
public class ClientHelper {
  private final JsonObject object;
  private final Vertx vertx;
  @Getter
  private static RabbitMQClient client;

  /**
   * 初始化mail client连接
   */
  public void afterPropertiesSet() {
    ConfigProperties.MqProperties prop = object.mapTo(ConfigProperties.MqProperties.class);

    RabbitMQOptions config = new RabbitMQOptions();
    // Each parameter is optional
    // The default parameter with be used if the parameter is not set
    config.setUser(prop.getUsername());
    config.setPassword(prop.getPassword());
    config.setHost(prop.getHost());
    config.setPort(prop.getPort());
    config.setVirtualHost(prop.getVirtualHost());
    config.setReconnectAttempts(prop.getReconnectAttempts());
    config.setReconnectInterval(prop.getReconnectInterval());
    config.setConnectionTimeout(prop.getConnectionTimeout());
    config.setRequestedHeartbeat(prop.getRequestedHeartbeat());
    config.setHandshakeTimeout(prop.getHandshakeTimeout());
    config.setRequestedChannelMax(prop.getRequestedChannelMax());
    config.setNetworkRecoveryInterval(prop.getNetworkRecoveryInterval());
    config.setAutomaticRecoveryEnabled(prop.isAutomaticRecoveryEnabled());
    client = RabbitMQClient.create(vertx, config);

    // Connect
    client.start(asyncResult -> {
      if (asyncResult.succeeded()) {
        consumer();
        log.warn("RabbitMQ successfully connected!");
      } else {
        log.error("Fail to connect to RabbitMQ {}", asyncResult.cause().getMessage());
      }
    });
  }

  private void consumer() {
    client.basicConsumer(MqConst.HELLO_ROUTING_KEY, result -> {
      if (result.succeeded()) {
        RabbitMQConsumer mqConsumer = result.result();
        mqConsumer.handler(message -> {
          log.info("Got message: {}", message.body().toString());
          log.info("Message[exchange: {}, routeKey: {}] receive success!", message.envelope().getExchange(), message.envelope().getRoutingKey());
        });
      } else {
        log.error("===>Queue receive fail: ", result.cause());
      }
    });
  }
}
