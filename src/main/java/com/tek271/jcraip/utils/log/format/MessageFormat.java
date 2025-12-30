package com.tek271.jcraip.utils.log.format;

import com.tek271.jcraip.utils.log.LogLevel;
import org.apache.commons.lang3.StringUtils;

import java.lang.StackWalker.StackFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.tek271.jcraip.utils.log.format.MessagePartType.*;

/**
 * Define a format for a logging message
 */
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

  /** Prevent changing the current instance of this object */
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

  /** Add a log level to the message format  */
  public MessageFormat level() {
    return add(new MessagePart(level));
  }

  /** Add current date to the message format  */
  public MessageFormat date(String dateFormat) {
    MessagePart part = new MessagePart(date);
    part.setDateFormat(dateFormat);
    return add(part);
  }

  public MessageFormat date() {
    return date(DATE_FORMAT_DEFAULT);
  }

  /** Add current time to the message format  */
  public MessageFormat time(String timeFormat) {
    MessagePart part = new MessagePart(time);
    part.setTimeFormat(timeFormat);
    return add(part);
  }

  public MessageFormat time() {
    return time(TIME_FORMAT_DEFAULT);
  }

  /**
   * add class name to the message format
   * @param packageDepth how many package level should be shown with class name. Zero means no package names
   * @return this object
   */
  public MessageFormat className(int packageDepth) {
    MessagePart part = new MessagePart(className);
    part.setPackageDepth(packageDepth);
    return add(part);
  }

  /** add class name to the message format with package depth = PACKAGE_DEPTH_DEFAULT */
  public MessageFormat className() {
    return className(PACKAGE_DEPTH_DEFAULT);
  }

  /** add method name to the message format */
  public MessageFormat method() {
    return add(new MessagePart(method));
  }

  /**
   * add a throwable to the message format
   * @param stackDepth depth of the stack trace to log
   * @return this object
   */
  public MessageFormat throwable(int stackDepth) {
    MessagePart part = new MessagePart(throwable);
    part.setStackDepth(stackDepth);
    return add(part);
  }

  /** add a throwable to the message format with depth = STACK_DEPTH_DEFAULT */
  public MessageFormat throwable() {
    return throwable(STACK_DEPTH_DEFAULT);
  }

  /** add message to the message format */
  public MessageFormat message() {
    MessagePart part = new MessagePart(message);
    return add(part);
  }

  /** add a place-holder to the message format */
  public MessageFormat placeholder(String placeholder) {
    MessagePart part = new MessagePart(MessagePartType.placeHolder);
    part.setPlaceholder(placeholder);
    return add(part);
  }

  /**
   * Add space to the message format
   * @param count number of spaces to add
   * @return this object
   */
  public MessageFormat space(int count) {
    return placeholder(StringUtils.repeat(' ', count));
  }

  /** Add a single space to the message format */
  public MessageFormat space() {
    return space(1);
  }

  List<String> buildMessageLines(LogLevel logLevel, StackFrame stackFrame, String message, Throwable throwable) {
    return parts.stream()
      .map(p -> p.toString(logLevel, stackFrame, message, throwable))
      .collect(Collectors.toList());
  }

  /**
   * Build a log message using the current format and the given arguments
   */
  public String buildMessage(LogLevel logLevel, StackFrame stackFrame, String message, Throwable throwable) {
    return String.join("", buildMessageLines(logLevel, stackFrame, message, throwable));
  }

}
