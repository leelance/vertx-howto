package com.lance.common.core.result;

import io.vertx.core.logging.Log4j2LogDelegateFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ResultTests {
  private static final Logger log = LogManager.getLogger(ResultTests.class);

  @BeforeAll
  public static void init() {
    System.setProperty("vertx.logger-delegate-factory-class-name", Log4j2LogDelegateFactory.class.getName());
  }

  @Test
  void create() {
    R<String> result = R.success("ok");
    log.info("===>{}", result);
  }
}
