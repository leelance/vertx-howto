package com.lance.mysql.service;

import com.lance.common.core.result.R;
import com.lance.mysql.config.DbHelper;
import com.lance.mysql.domain.UserInfo;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mysqlclient.MySQLPool;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.Tuple;
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

  /**
   * find list
   */
  public void list(RoutingContext ctx) {
    MySQLPool pool = DbHelper.client();
    pool.query("select *From t_user").mapping(UserInfo.row2User()).execute(rs -> {
      if (rs.succeeded()) {
        RowSet<UserInfo> result = rs.result();
        List<UserInfo> list = new ArrayList<>();
        result.forEach(list::add);
        ctx.json(R.data(list));
      } else {
        log.warn("Failure: ", rs.cause());
      }
    });
  }

  /**
   * find one
   */
  public void detail(RoutingContext ctx) {
    String userId = ctx.pathParam("userId");
    MySQLPool pool = DbHelper.client();
    pool.preparedQuery("select *From t_user where user_id=?").mapping(UserInfo.row2User()).execute(Tuple.of(userId), rs -> {
      if (rs.succeeded()) {
        RowSet<UserInfo> result = rs.result();
        if (result.size() > 0) {
          ctx.json(R.data(result.iterator().next()));
          return;
        }
        ctx.json(R.data(null));
      } else {
        log.warn("Failure: ", rs.cause());
      }
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
    pool.preparedQuery("insert into t_user(username,password,age,status,create_time,update_time)value(?,?,?,1,now(),now())")
        .execute(Tuple.of(user.getUsername(), user.getPassword(), user.getAge()), rs -> {
          if (rs.succeeded()) {
            ctx.json(R.success("success"));
          } else {
            log.warn("Failure: ", rs.cause());
            ctx.json(R.fail("fail"));
          }
        });
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

    MySQLPool pool = DbHelper.client();
    pool.preparedQuery("update t_user set username=?,password=?,age=?,status=?,update_time=now() where user_id=?")
        .execute(Tuple.of(user.getUsername(), user.getPassword(), user.getAge(), user.getStatus(), user.getUserId()), rs -> {
          if (rs.succeeded()) {
            ctx.json(R.success("success"));
          } else {
            log.warn("Failure: ", rs.cause());
            ctx.json(R.fail("fail"));
          }
        });
  }

  /**
   * delete one
   */
  public void delete(RoutingContext ctx) {
    String userId = ctx.pathParam("userId");
    MySQLPool pool = DbHelper.client();
    pool.preparedQuery("delete From t_user where user_id=?").execute(Tuple.of(userId), rs -> {
      if (rs.succeeded()) {
        ctx.json(R.data("success"));
      } else {
        log.warn("Failure: ", rs.cause());
        ctx.json(R.fail("fail"));
      }
    });
  }
}
