package com.lance.mail.service;

import com.lance.common.core.result.R;
import com.lance.mail.config.MailHelper;
import com.lance.mail.vo.UserVo;
import io.vertx.ext.mail.MailMessage;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;

/**
 * user service
 *
 * @author lance
 * @date 2022/1/13 0:05
 */
@Slf4j
public class UserService {

	public void sendMessage(RoutingContext ctx) {
		UserVo userVo = ctx.getBodyAsJson().mapTo(UserVo.class);
		MailMessage message = new MailMessage();
		message.setFrom(MailHelper.getFrom());
		message.setTo(userVo.getTo());
		message.setCc(userVo.getCc());
		message.setText(userVo.getContent());
		message.setSubject(userVo.getSubject());

		MailHelper.getMailClient().sendMail(message)
				.onSuccess(r -> {
					log.info("===>send content mail success: {}", r);
					ctx.json(R.data(r.toJson()));
				})
				.onFailure(e -> {
					log.error("===> send content mail fail: ", e);
					ctx.json(R.data(e.getMessage()));
				});

	}
}
