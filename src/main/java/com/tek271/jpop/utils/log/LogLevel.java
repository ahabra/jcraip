package com.tek271.jpop.utils.log;

public enum LogLevel {
  TRACE(0),
  DEBUG(1),
  INFO(2),
  WARN(3),
  ERROR(4);

  public final int code;

  LogLevel(int code) {
    this.code = code;
  }

  public static String toString(LogLevel logLevel) {
    if (logLevel == null) {
      logLevel = TRACE;
    }
    return logLevel.toString();
  }

}
