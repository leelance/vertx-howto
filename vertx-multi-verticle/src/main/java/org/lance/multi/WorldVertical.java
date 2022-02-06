package org.lance.multi;

import com.lance.common.core.result.R;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lance.multi.prop.ConfigProperties;

/**
 * hello vertical
 *
 * @author lance
 * @date 2022/1/26 18:02
 */
@Slf4j
@AllArgsConstructor
public class WorldVertical extends AbstractVerticle {
  private final Router router;

  @Override
  public void start() {
    router.route("/world").handler(ctx -> {
      String name = ctx.queryParam("name").get(0);

      log.info("===> Request world name: {}", name);
      ctx.json(R.success("World " + name));
    });

    ConfigProperties properties = config().mapTo(ConfigProperties.class);
    int port = properties.getServer().getPort();
    vertx.createHttpServer()
        .requestHandler(router)
        .listen(port, http -> {
          if (http.succeeded()) {
            log.info("World server started on port {}", http.result().actualPort());
          } else {
            log.error("World server start fail: ", http.cause());
          }
        });
  }
}