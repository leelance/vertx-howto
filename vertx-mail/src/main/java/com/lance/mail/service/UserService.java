package com.lance.mail.service;

import com.lance.common.core.result.R;
import com.lance.mail.config.MailHelper;
import com.lance.mail.vo.UserVo;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.ext.mail.MailAttachment;
import io.vertx.ext.mail.MailMessage;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * user service
 *
 * @author lance
 * @date 2022/1/13 0:05
 */
@Slf4j
public class UserService {
  private final static String SUFFIX_JAR = ".jar!";

  /**
   * send text content
   */
  public void sendMessage(RoutingContext ctx) {
    UserVo userVo = ctx.getBodyAsJson().mapTo(UserVo.class);
    userVo.setHtml(false);
    send(ctx, userVo, null);
  }

  /**
   * send html content
   */
  public void sendHtmlMessage(RoutingContext ctx) {
    UserVo userVo = ctx.getBodyAsJson().mapTo(UserVo.class);
    String html = "This is html text <a href=\"https://github.com/leelance/vertx-howto\" target=\"_blank\">Vertx--How To</a>";
    userVo.setContent(html);
    userVo.setHtml(true);
    send(ctx, userVo, null);
  }

  /**
   * send attach content
   */
  public void sendAttachMessage(RoutingContext ctx) {
    UserVo userVo = ctx.getBodyAsJson().mapTo(UserVo.class);
    String html = "<h1>Hello world.</h1><p>This is attach text <a href=\"https://github.com/leelance/vertx-howto\" target=\"_blank\">Vertx--How To</a></p>";
    userVo.setContent(html);
    userVo.setHtml(true);

    FileSystem fs = Vertx.vertx().fileSystem();
    String path = getPathString("attach/file.txt");
    Buffer buffer = fs.readFileBlocking(path);

    MailAttachment attachment = MailAttachment.create();
    attachment.setContentType("text/plain");
    attachment.setData(buffer);
    attachment.setName(new File(path).getName());
    attachment.setDescription("Attachments can be created by the MailAttachment object using data stored in a Buffer, this supports base64 attachments.");

    send(ctx, userVo, Collections.singletonList(attachment));
  }

  private void send(RoutingContext ctx, UserVo userVo, List<MailAttachment> attachments) {
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

    if (attachments != null) {
      message.setAttachment(attachments);
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

  /**
   * 获得根目录如果在jar中运行获得相对路径,反则返回当前线程运行的根目录
   *
   * @param fileName filename
   * @return path
   */
  private String getPathString(String fileName) {
    if (StringUtils.isBlank(fileName)) {
      throw new RuntimeException("filename is null");
    }

    URL path = Thread.currentThread().getContextClassLoader().getResource(fileName);
    if (path != null && path.getPath().contains(SUFFIX_JAR)) {
      return fileName;
    } else {
      return path == null ? "" : path.getPath();
    }
  }
}
