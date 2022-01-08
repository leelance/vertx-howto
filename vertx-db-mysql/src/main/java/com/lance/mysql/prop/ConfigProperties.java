package com.lance.mysql.prop;

import com.lance.mysql.config.DbHelper;
import lombok.Data;

/**
 * config properties
 *
 * @author lance
 * @date 2022/1/6 0:00
 */
@Data
public class ConfigProperties {
	/**
	 * mysql config
	 */
	private MysqlProperties mysql;
	/**
	 * server config
	 */
	private ServerProperties server;
	/**
	 * db
	 */
	private DbHelper db;

	@Data
	public static class MysqlProperties {
		private String host;
		private int port = 3306;
		private String database;
		private String username;
		private String password;
		private String charset = "utf8";
		private String collation = "utf8_general_ci";

		private int maxSize = 10;
		private int reconnectAttempts = 3;
		private int reconnectInterval = 1000;
		private String poolName = "p-mysql";
	}

	@Data
	public static class ServerProperties {
		private int port;
	}
}
