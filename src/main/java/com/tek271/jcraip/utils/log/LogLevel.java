package com.tek271.jcraip.utils.log;

public enum LogLevel {
  TRACE,
  DEBUG,
  INFO,
  WARN,
  ERROR;

  public static String toString(LogLevel logLevel) {
    if (logLevel == null) {
      logLevel = TRACE;
    }
    return logLevel.toString();
  }

}
