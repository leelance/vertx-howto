package com.lance.common.core.result;

import lombok.AllArgsConstructor;

import java.io.Serializable;

/**
 * 错误码定义接口
 *
 * @author lance
 * @date 2021/9/18 17:31
 */
public interface IResultCode extends Serializable {

  /**
   * 消息
   *
   * @return String
   */
  String getMessage();

  /**
   * 状态码
   *
   * @return int
   */
  String getCode();

  /**
   * 服务错误码前缀
   */
  @AllArgsConstructor
  enum P {
    /**
     * demo系统
     */
    RM("101", "demo测试服务"),
    ;

    private final String prefix;
    private final String desc;

    public String prefix() {
      return this.prefix;
    }
  }
}
