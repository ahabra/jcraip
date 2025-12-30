package com.tek271.jcraip.utils.log.format;

import com.google.common.base.Splitter;
import com.tek271.jcraip.utils.log.LogLevel;
import org.apache.commons.lang3.ArrayUtils;

import java.lang.StackWalker.StackFrame;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Define a part of a logging message
 */
public class MessagePart {
  private final MessagePartType type;
  private String placeholder = "";
  private DateTimeFormatter dateFormatter;
  private DateTimeFormatter timeFormatter;
  private int packageDepth;
  private int stackDepth;


  public MessagePart(MessagePartType type) {
    this.type = type;
  }

  public String toString(LogLevel logLevel, StackFrame stackFrame, String message, Throwable throwable) {
    return switch (type) {
      case level -> LogLevel.toString(logLevel);
      case date -> LocalDate.now().format(dateFormatter);
      case time -> LocalDateTime.now().format(timeFormatter);
      case className -> extractClassName(stackFrame);
      case method -> stackFrame == null ? "" : stackFrame.getMethodName();
      case message -> message;
      case throwable -> extractThrowable(throwable);
      case placeHolder -> this.placeholder;
    };
  }

  public void setPlaceholder(String placeholder) {
    this.placeholder = placeholder;
  }

  public void setDateFormat(String dateFormat) {
    this.dateFormatter = DateTimeFormatter.ofPattern(dateFormat);
  }

  public void setTimeFormat(String timeFormat) {
    this.timeFormatter = DateTimeFormatter.ofPattern(timeFormat);
  }

  public void setPackageDepth(int packageDepth) {
    this.packageDepth = packageDepth;
  }

  public void setStackDepth(int stackDepth) {
    this.stackDepth = stackDepth;
  }

  private String extractClassName(StackFrame stackFrame) {
    if (stackFrame == null) {
      return "";
    }

    Class<?> cls = stackFrame.getDeclaringClass();
    if (packageDepth == 0) {
      return cls.getSimpleName();
    }
    List<String> parts = Splitter.on('.').splitToList(cls.getName());
    int start = Math.max(0, parts.size() - packageDepth - 1);
    parts = parts.subList(start, parts.size());

    return String.join(".", parts);
  }

  private String extractThrowable(Throwable throwable) {
    if (throwable == null) {
      return "";
    }
    List<String> list = new ArrayList<>();
    list.add("");
    list.add("\t" + throwable.toString());

    StackTraceElement[] stackTrace = throwable.getStackTrace();
    if (ArrayUtils.isEmpty(stackTrace)) {
      return String.join("\n", list);
    }

    int len = Math.min(stackTrace.length, stackDepth);
    for (int i=0; i < len; i++) {
      list.add("\t\t" + stackTrace[i].toString());
    }

    return String.join("\n", list);
  }

}
