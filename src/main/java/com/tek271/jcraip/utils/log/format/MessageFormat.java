package com.tek271.jcraip.utils.log.format;

import com.tek271.jcraip.utils.log.LogLevel;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tek271.jcraip.utils.log.format.MessagePartType.*;
import static org.apache.commons.lang3.StringUtils.repeat;

public class MessageFormat {
  static final String DATE_FORMAT_DEFAULT = "yyyy.MM.dd";
  static final String TIME_FORMAT_DEFAULT = "HH:mm:ss";
  static final int PACKAGE_DEPTH_DEFAULT = 1;
  static final int STACK_DEPTH_DEFAULT = 5;
  static final String LOCK_ERR = "Can't change format after it is locked.";

  private final List<MessagePart> parts = new ArrayList<>();
  private boolean isLocked = false;

  public static final MessageFormat DEFAULT_FORMAT = new MessageFormat()
    .level().space().date().placeholder("-").time().space()
    .className().placeholder(".").method().placeholder("() ")
    .message().throwable().lock();

  public MessageFormat clear() {
    checkLock();
    parts.clear();
    return this;
  }

  public MessageFormat lock() {
    isLocked = true;
    return this;
  }

  public MessageFormat add(MessagePart part) {
    checkLock();
    parts.add(part);
    return this;
  }

  private void checkLock() {
    if (isLocked) {
      throw new IllegalStateException(LOCK_ERR);
    }
  }

  public MessageFormat level() {
    return add(new MessagePart(level));
  }

  public MessageFormat date(String dateFormat) {
    MessagePart part = new MessagePart(date);
    part.setDateFormat(dateFormat);
    return add(part);
  }

  public MessageFormat date() {
    return date(DATE_FORMAT_DEFAULT);
  }

  public MessageFormat time(String timeFormat) {
    MessagePart part = new MessagePart(time);
    part.setTimeFormat(timeFormat);
    return add(part);
  }

  public MessageFormat time() {
    return time(TIME_FORMAT_DEFAULT);
  }

  public MessageFormat className(int packageDepth) {
    MessagePart part = new MessagePart(className);
    part.setPackageDepth(packageDepth);
    return add(part);
  }

  public MessageFormat className() {
    return className(PACKAGE_DEPTH_DEFAULT);
  }

  public MessageFormat method() {
    return add(new MessagePart(method));
  }

  public MessageFormat throwable(int stackDepth) {
    MessagePart part = new MessagePart(throwable);
    part.setStackDepth(stackDepth);
    return add(part);
  }

  public MessageFormat throwable() {
    return throwable(STACK_DEPTH_DEFAULT);
  }

  public MessageFormat message() {
    MessagePart part = new MessagePart(message);
    return add(part);
  }

  public MessageFormat placeholder(String placeholder) {
    MessagePart part = new MessagePart(MessagePartType.placeHolder);
    part.setPlaceholder(placeholder);
    return add(part);
  }

  public MessageFormat space(int count) {
    return placeholder(repeat(' ', count));
  }

  public MessageFormat space() {
    return space(1);
  }

  List<String> buildMessageLines(LogLevel logLevel, Method method, String message, Throwable throwable) {
    return parts.stream()
      .map(p -> p.toString(logLevel, method, message, throwable))
      .collect(Collectors.toList());
  }

  public String buildMessage(LogLevel logLevel, Method method, String message, Throwable throwable) {
    return String.join("", buildMessageLines(logLevel, method, message, throwable));
  }

}
