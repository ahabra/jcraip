package com.tek271.jcraip.utils.log;

import java.util.Objects;

public class LogWriter {
  public final LogLevel logLevel;
  private final LogConfig logConfig = LogConfig.instance;

  public LogWriter(LogLevel logLevel) {
    this.logLevel = logLevel;
  }

  public void log(Object message) {
    if (noLogging()) {
      return;
    }
    // FIXME method?
    String msg = logConfig.messageFormat.buildMessage(logLevel, null, Objects.toString(message), null);
    System.out.println(msg);
  }

  public void log(String message, Object... args) {
    if (noLogging()) {
      return;
    }
    // FIXME method?
    String msg = logConfig.messageFormat.buildMessage(logLevel, null, format(message, args), null);
  }

  public void log(Throwable throwable) {
    if (noLogging()) {
      return;
    }
    // FIXME method?
    String msg = logConfig.messageFormat.buildMessage(logLevel, null, null, throwable);
  }

  public void log(Throwable throwable, String message, Object... args) {
    if (noLogging()) {
      return;
    }
    // FIXME method?
    String msg = logConfig.messageFormat.buildMessage(logLevel, null, format(message, args), throwable);
  }

  private String format(String message, Object... args) {
    return String.format(message, args);
  }

  private boolean noLogging() {
    if (logLevel.code < logConfig.logLevel.code) {
      return true;
    }
    return false;
  }

}
