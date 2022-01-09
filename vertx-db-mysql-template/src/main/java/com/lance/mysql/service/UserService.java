package com.lance.mysql.service;

import com.lance.common.core.result.R;
import com.lance.mysql.config.DbHelper;
import com.lance.mysql.domain.UserInfo;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.templates.SqlTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    MySQLPool pool = DbHelper.client();
    SqlTemplate.forQuery(pool, select(null))
        .mapTo(UserInfo.class)
        .execute(null)
        .onSuccess(rs -> {
          List<UserInfo> list = new ArrayList<>();
          rs.forEach(list::add);
          ctx.json(R.data(list));
        }).onFailure(e -> {
          log.warn("Failure: ", e);
          ctx.json(R.fail("list fail"));
        });
  }

  /**
   * find one
   */
  public void detail(RoutingContext ctx) {
    MySQLPool pool = DbHelper.client();
    SqlTemplate.forQuery(pool, select("where user_id=#{userId}"))
        .mapTo(UserInfo.class)
        .execute(Collections.singletonMap("userId", ctx.pathParam("userId")))
        .onSuccess(result -> {
          if (result.size() > 0) {
            ctx.json(R.data(result.iterator().next()));
            return;
          }
          ctx.json(R.data(null));
        }).onFailure(e -> {
          log.warn("Failure: ", e);
          ctx.json(R.fail("detail fail"));
        });
  }

  /**
   * add user info
   */
  public void add(RoutingContext ctx) {
    UserInfo user = ctx.getBodyAsJson().mapTo(UserInfo.class);
    if (user == null) {
      ctx.json(R.fail("参数为空"));
      return;
    }

    MySQLPool pool = DbHelper.client();
    SqlTemplate.forUpdate(pool, "insert into t_user(username,password,age,status,create_time,update_time)value(#{username},#{password},#{age},1,now(),now())")
        .mapFrom(UserInfo.class)
        .execute(user)
        .onSuccess(v -> ctx.json(R.success("save success")))
        .onFailure(e -> {
          log.warn("Failure: ", e);
          ctx.json(R.fail("save fail"));
        });
  }

  /**
   * update user
   */
  public void update(RoutingContext ctx) {
    log.info("===>{}", ctx.getBodyAsJson());
    UserInfo user = ctx.getBodyAsJson().mapTo(UserInfo.class);
    if (user == null) {
      ctx.json(R.fail("参数为空"));
      return;
    }

    MySQLPool pool = DbHelper.client();
    SqlTemplate.forUpdate(pool, "update t_user set username=#{username},password=#{password},age=#{age},status=#{status},update_time=now() where user_id=#{userId}")
        .mapFrom(UserInfo.class)
        .execute(user)
        .onSuccess(v -> ctx.json(R.success("update success")))
        .onFailure(e -> {
          log.warn("Failure: ", e);
          ctx.json(R.fail("update fail"));
        });
  }

  /**
   * delete one
   */
  public void delete(RoutingContext ctx) {
    MySQLPool pool = DbHelper.client();
    SqlTemplate.forUpdate(pool, "delete From t_user where user_id=#{userId}")
        .execute(Collections.singletonMap("userId", ctx.pathParam("userId")))
        .onSuccess(v -> ctx.json(R.success("delete success")))
        .onFailure(e -> {
          log.warn("Failure: ", e);
          ctx.json(R.fail("delete fail"));
        });
  }

  private String select(String where) {
    String select = "SELECT user_id as userId, username,password,age,status,create_time as createTime,update_time as updateTime from t_user";
    if (StringUtils.isNotBlank(where)) {
      select += " " + where;
    }
    return select;
  }
}
