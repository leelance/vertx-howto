package com.lance.rabbit.prop;

import lombok.Data;

/**
 * config properties
 *
 * @author lance
 * @date 2022/1/5 18:24
 */
@Data
public class ConfigProperties {
  private MqProperties rabbit;
  private ServerProperties server;

  @Data
  public static class MqProperties {
    private String host;
    private int port;
    private String username;
    private String password;
    private String virtualHost;
    /**
     * milliseconds
     */
    private int connectionTimeout = 6000;
    /**
     * seconds
     */
    private int requestedHeartbeat = 60;
    /**
     * milliseconds
     */
    private int handshakeTimeout = 6000;
    private int requestedChannelMax = 5;
    /**
     * milliseconds
     */
    private int networkRecoveryInterval = 500;
    private boolean automaticRecoveryEnabled = true;

    private int reconnectAttempts = 0;
    private int reconnectInterval = 500;
  }

  @Data
  public static class ServerProperties {
    private int port;
    private String host;
  }
}
