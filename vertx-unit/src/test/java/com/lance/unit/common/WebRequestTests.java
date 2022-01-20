package com.lance.unit.common;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientResponse;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.junit5.Checkpoint;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.concurrent.TimeUnit;

/**
 * String test.
 *
 * @author lance
 * @date 2022/1/20 12:00
 */
@Slf4j
@ExtendWith(VertxExtension.class)
class WebRequestTests {
  private final Vertx vertx = Vertx.vertx();

  /**
   * test vertx server
   */
  @Test
  @Disabled
  void suite() {
    vertx.createHttpServer()
        .requestHandler(req -> req.response().end("Ok"))
        .listen(16969, ar -> {
          log.info("===> vertx start: {}", ar.succeeded());
        });
  }

  /**
   * 1.允许等待其他线程中的操作以通知完成, 类似java中promise对象
   * 2.支持接收断言失败以将测试标记为失败
   */
  @Test
  @Disabled
  void asyncContext() throws Throwable {
    VertxTestContext testContext = new VertxTestContext();

    vertx.createHttpServer()
        .requestHandler(req -> req.response().end())
        .listen(16969)
        .onComplete(testContext.succeedingThenComplete());
    //.onComplete(testContext.failingThenComplete());

    Assertions.assertTrue(testContext.awaitCompletion(5, TimeUnit.SECONDS));
    if (testContext.failed()) {
      throw testContext.causeOfFailure();
    }
  }

  /**
   * Test http client
   */
  @Test
  @Disabled
  void httpClient() throws Exception {
    HttpClient client = vertx.createHttpClient();
    VertxTestContext testContext = new VertxTestContext();

    client.request(HttpMethod.GET, 18002, "127.0.0.1", "/user/info/2")
        .compose(req -> req.send().compose(HttpClientResponse::body))
        .onComplete(testContext.succeeding(buffer -> testContext.verify(() -> {
          JsonObject jsonObject = buffer.toJsonObject();
          log.info("===>Response body: {}", jsonObject);
          Assertions.assertEquals(jsonObject.getString("code"), HttpResponseStatus.OK.reasonPhrase());
          testContext.completeNow();
        }))).onFailure(event -> log.error("===>fail: ", event.getCause()));

    boolean awaitResult = testContext.awaitCompletion(30, TimeUnit.SECONDS);
    log.info("===>Await: {}", awaitResult);
  }

  /**
   * 只需调用即可将许多测试标记为通过completeNow在执行的某个时间点。话虽如此，在许多情况下，测试的成功取决于要验证的不同异步部分。
   */
  @Test
  @Disabled
  void checkpoint() {
    int port = 9001;
    VertxTestContext testContext = new VertxTestContext();
    Checkpoint serverStarted = testContext.checkpoint();
    Checkpoint requestsServed = testContext.checkpoint(10);
    Checkpoint responsesReceived = testContext.checkpoint(10);

    vertx.createHttpServer()
        .requestHandler(req -> {
          req.response().end("success");
          requestsServed.flag();
        })
        .listen(port)
        .onComplete(testContext.succeeding(httpServer -> {
              serverStarted.flag();

              HttpClient client = vertx.createHttpClient();
              for (int i = 0; i < 10; i++) {
                client.request(HttpMethod.GET, port, "localhost", "/")
                    .compose(req -> req.send().compose(HttpClientResponse::body))
                    .onComplete(testContext.succeeding(buffer -> testContext.verify(() -> {
                          Assertions.assertEquals("success", buffer.toString());
                          responsesReceived.flag();
                        }))
                    );
              }
            })
        );
  }
}
