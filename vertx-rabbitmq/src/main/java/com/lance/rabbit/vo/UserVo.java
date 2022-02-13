package com.lance.rabbit.vo;

import lombok.Data;

import java.util.List;

/**
 * user
 *
 * @author lance
 * @date 2021/12/30 09:56
 */
@Data
public class UserVo {
  /**
   * 收件人
   */
  private List<String> to;
  /**
   * 抄送人
   */
  private List<String> cc;
  /**
   * 发送内容
   */
  private String content;
  /**
   * 邮件主题
   */
  private String subject;
  /**
   * 是否是html内容
   */
  private boolean isHtml = false;
}
