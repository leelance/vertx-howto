package com.lance.config.handler;

import com.lance.common.core.exception.ServiceException;
import com.lance.common.core.result.R;
import com.lance.common.core.result.ResultCode;
import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认异常处理
 *
 * @author lance
 * @date 2022/1/4 21:10
 */
@Slf4j
public final class DefaultExceptionHandler implements Handler<RoutingContext> {

	private DefaultExceptionHandler() {

	}

	@Override
	public void handle(RoutingContext ctx) {
		Throwable throwable = ctx.failure();
		log.error("===> Default exception fail: ", throwable);

		if (throwable instanceof NullPointerException) {
			ctx.json(R.fail("空指针异常错误."));
			return;
		}

		if (throwable instanceof ServiceException) {
			ServiceException ex = (ServiceException) throwable;
			ctx.json(R.fail(ex.getCode(), ex.getMessage()));
			return;
		}

		// 定义其他异常类型
		// .......

		ctx.json(R.fail(ResultCode.FAILURE));
	}

	public static DefaultExceptionHandler of() {
		return new DefaultExceptionHandler();
	}
}
