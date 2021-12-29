package com.lance.common.core.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 定义基本的错误码, 参考HttpServlet
 *
 * @author lance
 * @date 2021/10/25 11:26
 */
@Getter
@AllArgsConstructor
public enum ResultCode implements IResultCode {

  /**
   * 操作成功
   */
  SUCCESS("200", "操作成功", "Success"),
  FAILURE("10000", "操作失败", "Failure"),
  ;

  /**
   * code编码
   */
  final String code;
  /**
   * 中文信息描述
   */
  final String message;
  /**
   * 英文信息
   *
   * @since 2021.10.24
   */
  final String messageEn;
}
