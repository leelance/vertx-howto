package org.lance.multi.prop;

import lombok.Data;

/**
 * config properties
 *
 * @author lance
 * @date 2022/1/5 18:24
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
