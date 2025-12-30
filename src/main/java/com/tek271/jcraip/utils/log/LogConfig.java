package com.tek271.jcraip.utils.log;

import com.tek271.jcraip.utils.log.format.MessageFormat;

import java.util.HashSet;
import java.util.Set;

public class LogConfig {
  public static final LogConfig instance = new LogConfig();

  public LogLevel logLevel = LogLevel.DEBUG;
  public MessageFormat messageFormat = MessageFormat.DEFAULT_FORMAT;
  public final Set<Class<?>> callersThatLog = new HashSet<>();

  private LogConfig() {}

  /** Call this specially in tests */
  public void reset() {
    logLevel = LogLevel.DEBUG;
    messageFormat = MessageFormat.DEFAULT_FORMAT;
    callersThatLog.clear();
  }

}
