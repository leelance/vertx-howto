package org.lance.multi;

import com.lance.common.core.result.R;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lance.multi.prop.ConfigProperties;

import java.util.Map;

/**
 * hello
 *
 * @author lance
 * @date 2022/1/26 18:10
 */
@Slf4j
@AllArgsConstructor
public class HelloVertical extends AbstractVerticle {
  private final Router router;

  @Override
  public void start() {
    router.route("/hello").handler(ctx -> {
      String name = ctx.queryParam("name").get(0);
      Map<String, Object> map = ctx.data();

      log.info("===> Request hello name: {}, data: {}", name, map);
      ctx.json(R.success("Hello " + name));
    });

    ConfigProperties properties = config().mapTo(ConfigProperties.class);
    int port = properties.getServer().getPort();

    vertx.createHttpServer()
        .requestHandler(router)
        .listen(port, http -> {
          if (http.succeeded()) {
            log.info("Hello server started on port {}", http.result().actualPort());
          } else {
            log.error("Hello server start fail: ", http.cause());
          }
        });
  }
}
