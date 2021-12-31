package com.lance.route.restful.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * user
 *
 * @author lance
 * @date 2021/12/30 09:56
 */
@Data
@AllArgsConstructor
public class UserVo {
  private Long userId;
  private String username;
  private String password;
  private int age;

  public static UserVo of(Long userId, String username, String password, int age) {
    return new UserVo(userId, username, password, age);
  }
}
