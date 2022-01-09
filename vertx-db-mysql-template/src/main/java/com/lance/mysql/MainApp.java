package com.lance.mysql;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.lance.mysql.prop.ConfigProperties;
import com.lance.mysql.web.MainRoute;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.jackson.DatabindCodec;
import lombok.extern.slf4j.Slf4j;

/**
 * Main
 *
 * @author lance
 * @date 2021/12/12 23:30
 */
@Slf4j
public class MainApp extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    //初始化相关配置
    init();

    HttpServer server = vertx.createHttpServer();
    server.requestHandler(new MainRoute().create(vertx));

    ConfigProperties properties = config().mapTo(ConfigProperties.class);
    int port = properties.getServer().getPort();
    log.info("===>json: {}, port: {}", properties, port);
    server.listen(port, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        log.info("HTTP server started on port {}", port);
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

  /**
   * 初始化相关配置
   */
  private void init() {
    ObjectMapper mapper = DatabindCodec.mapper();
    mapper.registerModule(new JavaTimeModule());
  }
}
