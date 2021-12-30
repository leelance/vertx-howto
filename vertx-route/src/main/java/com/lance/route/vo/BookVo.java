package com.lance.route.vo;

import lombok.Data;

import java.util.Date;

/**
 * book
 *
 * @author lance
 * @date 2021/12/30 09:57
 */
@Data
public class BookVo {
  private Long bookId;
  private String bookName;
  private String bookAuthor;
  private Date publishDate;
}
