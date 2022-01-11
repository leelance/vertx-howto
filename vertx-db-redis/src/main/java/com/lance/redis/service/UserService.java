package com.lance.redis.service;

import com.lance.common.core.result.R;
import com.lance.redis.domain.UserInfo;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

/**
 * user service
 *
 * @author lance
 * @date 2021/12/30 10:33
 */
@Slf4j
public class UserService {

  /**
   * find list
   */
  public void list(RoutingContext ctx) {
    ctx.json(R.data("list"));
  }

  /**
   * find one
   */
  public void detail(RoutingContext ctx) {
    String userId = ctx.pathParam("userId");
    ctx.json(R.data(userId));
  }

  /**
   * add user info
   */
  public void add(RoutingContext ctx) {
    UserInfo user = ctx.getBodyAsJson().mapTo(UserInfo.class);
    ctx.json(R.data(user));
  }

  /**
   * update user
   */
  public void update(RoutingContext ctx) {
    UserInfo user = ctx.getBodyAsJson().mapTo(UserInfo.class);
    if (user == null) {
      ctx.json(R.fail("参数为空"));
      return;
    }

    ctx.json(R.data(user));
  }

  /**
   * delete one
   */
  public void delete(RoutingContext ctx) {
    String userId = ctx.pathParam("userId");
    ctx.json(R.data(userId));
  }
}
