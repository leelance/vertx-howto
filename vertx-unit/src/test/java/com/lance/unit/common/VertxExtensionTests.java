package com.lance.unit.common;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.TimeUnit;

/**
 * VertxExtension
 *
 * @author lance
 * @date 2022/1/20 15:15
 */
@Slf4j
@ExtendWith(VertxExtension.class)
class VertxExtensionTests {
  private final int port = 9000;

  @BeforeEach
  void init(Vertx vertx, VertxTestContext testContext) {
    vertx.deployVerticle(new HttpServerVertical(), testContext.succeedingThenComplete());
  }

  @AfterEach
  public void after(Vertx vertx) {
    log.info("===>Vertx close.");
    vertx.close();
  }

  /**
   * 连续测试3次
   * repetition 1 of 3
   * repetition 2 of 3
   * repetition 3 of 3
   */
  @Disabled
  @RepeatedTest(3)
  void request3Times(Vertx vertx, VertxTestContext testContext) {
    HttpClient client = vertx.createHttpClient();
    client.request(HttpMethod.GET, port, "localhost", "/")
        .compose(req -> req.send().compose(HttpClientResponse::body))
        .onComplete(testContext.succeeding(buffer -> testContext.verify(() -> {
          log.info("===>Response result: {}", buffer.toString());
          testContext.completeNow();
        })));
  }

  @Test
  @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
  void timeout(Vertx vertx, VertxTestContext testContext) {
    HttpClient client = vertx.createHttpClient();
    client.request(HttpMethod.GET, port, "localhost", "/")
        .compose(req -> req.send().compose(HttpClientResponse::body))
        .onComplete(testContext.succeeding(buffer -> testContext.verify(() -> {
          Thread.sleep(5000L);
          log.info("===>Response timeout result: {}", buffer.toString());
          testContext.completeNow();
        })));
  }

  /**
   * 单服务测试
   */
  @Test
  @Disabled
  void httpServerResponse(Vertx vertx, VertxTestContext testContext) {
    vertx.deployVerticle(new HttpServerVertical(), testContext.succeeding(id -> {

      HttpClient client = vertx.createHttpClient();
      client.request(HttpMethod.GET, port, "localhost", "/")
          .compose(req -> req.send().compose(HttpClientResponse::body))
          .onComplete(testContext.succeeding(buffer -> testContext.verify(() -> {
            log.info("===>Response result: {}", buffer.toString());
            Assertions.assertEquals(buffer.toString(), "success");
            testContext.completeNow();
          })));
    }));
  }

  class HttpServerVertical extends AbstractVerticle {
    @Override
    public void start() throws Exception {
      vertx.createHttpServer()
          .requestHandler(req -> {
            req.response().end("success");
          })
          .listen(port).onComplete(event -> {
            if (event.succeeded()) {
              log.info("===>Start [{}] success!", port);
              return;
            }

            log.error("===>Start fail: ", event.cause());
          });
    }
  }
}
