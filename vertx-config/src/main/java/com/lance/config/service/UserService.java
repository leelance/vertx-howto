package com.lance.config.service;

import com.lance.common.core.exception.ServiceException;
import com.lance.common.core.result.R;
import com.lance.config.prop.ConfigProperties;
import com.lance.config.vo.UserVo;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * user service
 *
 * @author lance
 * @date 2021/12/30 10:33
 */
@Slf4j
public class UserService {
  private final static List<UserVo> USERS = new ArrayList<>();

  static {
    USERS.add(UserVo.of(1L, "Jim Green", "abc@23456", 21));
    USERS.add(UserVo.of(2L, "Tom Dio", "abc@23456", 22));
    USERS.add(UserVo.of(3L, "Mrs Mei", "abc@23456", 23));
    USERS.add(UserVo.of(4L, "Tom Holland", "abc@23456", 24));
    USERS.add(UserVo.of(5L, "Zendaya", "abc@23456", 25));
  }

  public void list(RoutingContext ctx) {
    JsonObject jsonObject = Vertx.currentContext().config();
    ConfigProperties properties = jsonObject.mapTo(ConfigProperties.class);

    log.info("===> get all users, config: {}", properties.getUser());
    throw new RuntimeException("数据为空.");
  }

  public void detail(RoutingContext ctx) {
    String userId = ctx.pathParam("userId");
    log.info("===> detail user: {}", userId);

    throw ServiceException.of("10001", "用户Id查询失败,用户Id: " + userId);
  }

  public void add(RoutingContext ctx) {
    UserVo userVo = ctx.getBodyAsJson().mapTo(UserVo.class);
    log.info("===> save user: {}", userVo);
    USERS.add(userVo);
    ctx.json(R.success("save"));
  }

  public void update(RoutingContext ctx) {
    UserVo userVo = ctx.getBodyAsJson().mapTo(UserVo.class);
    log.info("===> update user: {}", userVo);
    ctx.json(R.success("update"));
  }

  public void delete(RoutingContext ctx) {
    String userId = ctx.pathParam("userId");
    log.info("===> delete user: {}", userId);
    ctx.json(R.success("delete"));
  }
}
