package org.lance.mqtt.prop;

import lombok.Data;

/**
 * config properties
 *
 * @author lance
 * @date 2022/1/21 20:05
 */
@Data
public class ConfigProperties {
  private ServerProperties server;

  @Data
  public static class ServerProperties {
    private int port;
    private String host;
  }
}
