package com.tek271.jcraip.utils.log;

public class Log {

  public LogWriter trace() {
    return new LogWriter(LogLevel.TRACE);
  }

  public LogWriter debug() {
    return new LogWriter(LogLevel.DEBUG);
  }

  public LogWriter info() {
    return new LogWriter(LogLevel.INFO);
  }

  public LogWriter warn() {
    return new LogWriter(LogLevel.WARN);
  }

  public LogWriter error() {
    return new LogWriter(LogLevel.ERROR);
  }

}
