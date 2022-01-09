package com.lance.mysql.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lance.mysql.utils.DateUtil;
import io.vertx.sqlclient.Row;
import lombok.Data;

import java.util.Date;
import java.util.function.Function;

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
  private Date createTime;
  /**
   * 更新时间
   */
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;

  public static Function<Row, UserInfo> row2User() {
    return row -> {
      UserInfo info = new UserInfo();
      info.setUserId(row.getLong("user_id"));
      info.setUsername(row.getString("username"));
      info.setPassword(row.getString("password"));
      info.setAge(row.getInteger("age"));
      info.setStatus(row.getInteger("status"));
      info.setCreateTime(DateUtil.toDate(row.getLocalDateTime("create_time")));
      info.setUpdateTime(DateUtil.toDate(row.getLocalDateTime("update_time")));
      return info;
    };
  }
}
