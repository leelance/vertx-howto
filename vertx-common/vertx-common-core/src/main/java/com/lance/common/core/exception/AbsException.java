package com.lance.common.core.exception;

/**
 * 抽象异常处理类
 *
 * @author lance
 * @date 2022/1/4 21:39
 */
public class AbsException extends RuntimeException {
	public AbsException() {
	}

	public AbsException(String message) {
		super(message);
	}

	public AbsException(String message, Throwable cause) {
		super(message, cause);
	}

	public AbsException(Throwable cause) {
		super(cause);
	}

	public AbsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
