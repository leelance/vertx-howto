package com.lance.redis.config;

import com.lance.redis.prop.ConfigProperties;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.client.Redis;
import io.vertx.redis.client.RedisAPI;
import io.vertx.redis.client.RedisClientType;
import io.vertx.redis.client.RedisOptions;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * db helper
 *
 * @author lance
 * @date 2022/1/8 20:05
 */
@Data
@Slf4j
@RequiredArgsConstructor
public class DbHelper {
	private final JsonObject object;
	private final Vertx vertx;
	@Getter
	private static RedisAPI redis;

	/**
	 * 初始化redis连接
	 */
	public void afterPropertiesSet() {
		ConfigProperties.RedisProperties properties = object.mapTo(ConfigProperties.RedisProperties.class);
		if (properties.getUrls() == null || properties.getUrls().length == 0) {
			throw new RuntimeException("Redis connect url is not null.");
		}

		RedisOptions options = new RedisOptions()
				.setType(properties.getClientType())
				.setPoolName(properties.getPoolName())
				.setMaxPoolSize(properties.getMaxPoolSize())
				.setMaxPoolWaiting(properties.getMaxPoolWaiting())
				.setPoolCleanerInterval(properties.getPoolCleanerInterval());

		// password
		if (StringUtils.isNotBlank(properties.getPassword())) {
			options.setPassword(properties.getPassword());
		}
		// connect address [redis://localhost:7000, redis://localhost:7001]
		for (String url : properties.getUrls()) {
			options.addConnectionString(url);
		}
		// sentinel
		if (properties.getClientType().equals(RedisClientType.SENTINEL)) {
			options.setRole(properties.getRole()).setMasterName(properties.getMasterName());
		}
		redis = RedisAPI.api(Redis.createClient(vertx, options));
	}
}
