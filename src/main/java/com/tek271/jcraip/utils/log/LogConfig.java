package com.tek271.jcraip.utils.log;

import com.tek271.jcraip.utils.log.format.MessageFormat;

public class LogConfig {
  public static final LogConfig instance = new LogConfig();

  public LogLevel logLevel = LogLevel.DEBUG;
  public MessageFormat messageFormat = MessageFormat.DEFAULT_FORMAT;
  public Class<?> logWhenCaller;

  private LogConfig() {}

}
