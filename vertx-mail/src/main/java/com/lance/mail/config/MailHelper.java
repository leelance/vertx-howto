package com.lance.mail.config;

import com.lance.mail.prop.ConfigProperties;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mail.MailClient;
import io.vertx.ext.mail.MailConfig;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * db helper
 *
 * @author lance
 * @date 2022/1/8 20:05
 */
@Data
@Slf4j
@RequiredArgsConstructor
public class MailHelper {
	private final JsonObject object;
	private final Vertx vertx;
	@Getter
	private static MailClient mailClient;
	@Getter
	private static String from;

	/**
	 * 初始化mail client连接
	 */
	public void afterPropertiesSet() {
		ConfigProperties.MailProperties properties = object.mapTo(ConfigProperties.MailProperties.class);
		from = properties.getUsername();

		MailConfig config = new MailConfig();
		config.setHostname(properties.getHostname());
		config.setUsername(from);
		config.setPassword(properties.getPassword());
		config.setPort(properties.getPort());
		config.setStarttls(properties.getStarttls());
		mailClient = MailClient.create(vertx, config);
	}
}
