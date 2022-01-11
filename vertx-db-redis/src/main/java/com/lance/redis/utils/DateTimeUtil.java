package com.lance.redis.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * DateTime 工具类
 *
 * @author lance
 * @date 8/25/2021 10:01
 */
public class DateTimeUtil {
  public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern(DateUtil.PATTERN_DATETIME);
  public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(DateUtil.PATTERN_DATE);
  public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern(DateUtil.PATTERN_TIME);

  /**
   * 日期时间格式化
   *
   * @param temporal 时间
   * @return 格式化后的时间
   */
  public static String formatDateTime(TemporalAccessor temporal) {
    return DATETIME_FORMAT.format(temporal);
  }

  /**
   * 日期时间格式化
   *
   * @param temporal 时间
   * @return 格式化后的时间
   */
  public static String formatDate(TemporalAccessor temporal) {
    return DATE_FORMAT.format(temporal);
  }

  /**
   * 时间格式化
   *
   * @param temporal 时间
   * @return 格式化后的时间
   */
  public static String formatTime(TemporalAccessor temporal) {
    return TIME_FORMAT.format(temporal);
  }

  /**
   * 日期格式化
   *
   * @param temporal 时间
   * @param pattern  表达式
   * @return 格式化后的时间
   */
  public static String format(TemporalAccessor temporal, String pattern) {
    return DateTimeFormatter.ofPattern(pattern).format(temporal);
  }

  /**
   * 将字符串转换为时间
   *
   * @param dateStr 时间字符串
   * @param pattern 表达式
   * @return 时间
   */
  public static TemporalAccessor parse(String dateStr, String pattern) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern(pattern);
    return format.parse(dateStr);
  }

  /**
   * 将字符串转换为时间
   *
   * @param dateStr   时间字符串
   * @param formatter DateTimeFormatter
   * @return 时间
   */
  public static TemporalAccessor parse(String dateStr, DateTimeFormatter formatter) {
    return formatter.parse(dateStr);
  }

  /**
   * 时间转 Instant
   *
   * @param dateTime 时间
   * @return Instant
   */
  public static Instant toInstant(LocalDateTime dateTime) {
    return dateTime.atZone(ZoneId.systemDefault()).toInstant();
  }

  /**
   * Instant 转 时间
   *
   * @param instant Instant
   * @return Instant
   */
  public static LocalDateTime toDateTime(Instant instant) {
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
  }
}
