package com.lance.rabbit.service;

import com.lance.common.core.result.R;
import com.lance.rabbit.config.ClientHelper;
import com.lance.rabbit.config.MqConst;
import com.lance.rabbit.vo.UserVo;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

/**
 * user service
 *
 * @author lance
 * @date 2022/1/13 0:05
 */
@Slf4j
public class UserService {

  /**
   * send text content
   */
  public void sendMessage(RoutingContext ctx) {
    UserVo userVo = ctx.getBodyAsJson().mapTo(UserVo.class);

    Buffer message = Buffer.buffer(Json.encode(userVo));
    ClientHelper.getClient().basicPublish(MqConst.HELLO_EXCHANGE, MqConst.HELLO_ROUTING_KEY, message, result -> {
      if (result.succeeded()) {
        log.info("Message[exchange: {}, routeKey: {}] published success!", MqConst.HELLO_EXCHANGE, MqConst.HELLO_ROUTING_KEY);
      } else {
        log.error("Message send fail: ", result.cause());
      }
    });

    ctx.json(R.success("success"));
  }
}
