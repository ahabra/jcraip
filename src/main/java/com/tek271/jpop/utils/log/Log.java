package com.tek271.jpop.utils.log;

public interface Log {
  LogWriter trace = new LogWriter(LogLevel.TRACE);
  LogWriter debug = new LogWriter(LogLevel.DEBUG);
  LogWriter info = new LogWriter(LogLevel.INFO);
  LogWriter warn = new LogWriter(LogLevel.WARN);
  LogWriter error = new LogWriter(LogLevel.ERROR);

  static void reset() {
    trace.reset();
    debug.reset();
    info.reset();
    warn.reset();
    error.reset();
  }

}
