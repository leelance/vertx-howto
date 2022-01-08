package com.lance.mysql.config;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * db helper
 *
 * @author lance
 * @date 2022/1/8 20:05
 */
@Slf4j
@UtilityClass
public class DbHelper {
  public final static DbHelper INSTANCE = new DbHelper();

  public String get() {
    log.info("===>db helper print...");
    return "ok";
  }
}
