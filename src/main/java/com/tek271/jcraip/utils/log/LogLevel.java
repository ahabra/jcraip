package com.tek271.jcraip.utils.log;

public enum LogLevel {
  TRACE,
  DEBUG,
  INFO,
  WARN,
  ERROR;

  public static String text(LogLevel logLevel) {
    if (logLevel == null) {
      logLevel = TRACE;
    }
    return logLevel.toString();
  }

}
