package com.lance.common.core.exception;

import com.lance.common.core.result.IResultCode;
import lombok.Getter;

/**
 * service exception
 *
 * @author lance
 * @date 2022/1/4 21:40
 */
public class ServiceException extends AbsException {
	private final static String DEFAULT_FAIL_CODE = "-1";
	private final static String DEFAULT_MESSAGE = "failure";
	@Getter
	private final String code;

	private ServiceException(String code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public static ServiceException of(String code) {
		return new ServiceException(code, DEFAULT_MESSAGE, null);
	}

	public static ServiceException of(Throwable cause) {
		return new ServiceException(DEFAULT_FAIL_CODE, DEFAULT_MESSAGE, cause);
	}

	public static ServiceException of(String message, Throwable cause) {
		return new ServiceException(DEFAULT_FAIL_CODE, message, cause);
	}

	public static ServiceException of(String code, String message) {
		return new ServiceException(code, message, null);
	}

	public static ServiceException of(String code, String message, Throwable cause) {
		return new ServiceException(code, message, cause);
	}

	public static ServiceException of(IResultCode errorCode) {
		return new ServiceException(errorCode.getCode(), errorCode.getMessage(), null);
	}
}

