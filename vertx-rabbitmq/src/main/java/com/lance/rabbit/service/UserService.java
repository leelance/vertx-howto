package com.lance.rabbit.service;

import com.lance.rabbit.vo.UserVo;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.ext.web.RoutingContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;

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
  }

  /**
   * send html content
   */
  public void sendHtmlMessage(RoutingContext ctx) {
    UserVo userVo = ctx.getBodyAsJson().mapTo(UserVo.class);
    String html = "This is html text <a href=\"https://github.com/leelance/vertx-howto\" target=\"_blank\">Vertx--How To</a>";
    userVo.setContent(html);
    userVo.setHtml(true);
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
