package com.lance.mysql.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * user
 *
 * @author lance
 * @date 2021/12/30 09:56
 */
@Data
public class UserInfo {
  /**
   * 用户id
   */
  private Long userId;
  /**
   * 账号
   */
  private String username;
  /**
   * 密码
   */
  private String password;
  /**
   * 年龄
   */
  private int age;
  /**
   * 状态
   */
  private int status;
  /**
   * 创建时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime createTime;
  /**
   * 更新时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private LocalDateTime updateTime;
}
