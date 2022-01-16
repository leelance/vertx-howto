package com.lance.metrics.prop;

import lombok.Data;

/**
 * config properties
 *
 * @author lance
 * @date 2022/1/16 10:23
 */
@Data
public class ConfigProperties {
  private ServerProperties server;
  private MetricsProperties metrics;

  @Data
  public static class ServerProperties {
    private int port;
  }

  @Data
  public static class MetricsProperties {
    private boolean enabled = false;
    private int port;
    private String endpoint;
  }
}
