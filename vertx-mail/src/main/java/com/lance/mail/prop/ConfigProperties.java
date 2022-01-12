package com.lance.mail.prop;

import lombok.Data;

import java.util.Date;

/**
 * config properties
 *
 * @author lance
 * @date 2022/1/5 18:24
 */
@Data
public class ConfigProperties {
  private UserProperties user;
  private ServerProperties server;

  @Data
  public static class UserProperties {
    private String name;
    private int age;
    private Date birth;
    private int sex;
  }

  @Data
  public static class ServerProperties {
    private int port;
  }
}
