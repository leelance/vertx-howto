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
    userVo.setHtml(false);
    send(ctx, userVo);
  }

  public void sendHtmlMessage(RoutingContext ctx) {
    UserVo userVo = ctx.getBodyAsJson().mapTo(UserVo.class);
    String html = "This is html text <a href=\"https://github.com/leelance/vertx-howto\" target=\"_blank\">Vertx--How To</a>";
    userVo.setContent(html);
    userVo.setHtml(true);
    send(ctx, userVo);
  }

  private void send(RoutingContext ctx, UserVo userVo) {
    MailMessage message = new MailMessage();
    message.setFrom(MailHelper.getFrom());
    message.setTo(userVo.getTo());
    message.setCc(userVo.getCc());
    message.setSubject(userVo.getSubject());
    if (userVo.isHtml()) {
      message.setHtml(userVo.getContent());
    } else {
      message.setText(userVo.getContent());
    }

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
