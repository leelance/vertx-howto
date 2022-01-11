package com.lance.redis.service;

import com.lance.common.core.result.R;
import com.lance.redis.config.DbHelper;
import com.lance.redis.domain.UserInfo;
import io.vertx.ext.web.RoutingContext;
import io.vertx.redis.client.RedisAPI;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

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

		RedisAPI redis = DbHelper.getRedis();
		redis.get("hello")
				.onSuccess(val -> ctx.json(R.data("===> detail result: " + val)))
				.onFailure(event -> ctx.json(R.fail(event.getMessage())));
	}

	/**
	 * add user info
	 */
	public void add(RoutingContext ctx) {
		RedisAPI redis = DbHelper.getRedis();

		redis.getset("hello", "Hello world!")
				.onSuccess(val -> ctx.json(R.data("===> add result: " + val)))
				.onFailure(event -> ctx.json(R.fail(event.getMessage())));
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
		RedisAPI redis = DbHelper.getRedis();
		redis.del(Arrays.asList("hello", userId))
				.onSuccess(val -> ctx.json(R.data("===> delete result:" + val)))
				.onFailure(event -> ctx.json(R.fail(event.getMessage())));
	}
}
