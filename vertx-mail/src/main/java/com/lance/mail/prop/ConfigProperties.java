package com.lance.mail.prop;

import io.vertx.ext.mail.StartTLSOptions;
import lombok.Data;

/**
 * config properties
 *
 * @author lance
 * @date 2022/1/5 18:24
 */
@Data
public class ConfigProperties {
	private MailProperties mail;
	private ServerProperties server;

	@Data
	public static class MailProperties {
		private String hostname;
		private String username;
		private String password;
		private int port;
		private StartTLSOptions starttls = StartTLSOptions.REQUIRED;
	}

	@Data
	public static class ServerProperties {
		private int port;
	}
}
