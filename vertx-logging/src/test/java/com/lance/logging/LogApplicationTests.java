package com.lance.logging;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * test
 *
 * @author lance
 * @date 2022/1/1 14:28
 */
@Slf4j
class LogApplicationTests {

  @Test
  void print() {
    log.info("===> hello world.");
  }
}
