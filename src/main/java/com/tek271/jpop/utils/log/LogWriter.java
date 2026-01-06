package com.tek271.jpop.utils.log;

import com.google.common.annotations.VisibleForTesting;
import com.tek271.jpop.utils.reflect.StackTools;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.tek271.jpop.utils.reflect.StackTools.frameAt;
import static java.io.OutputStream.nullOutputStream;

public class LogWriter {
  public final LogLevel logLevel;
  private final LogConfig logConfig = LogConfig.instance;
  private List<String> logLines = null;
  private PrintStream printStream = System.out;

  public LogWriter(LogLevel logLevel) {
    this.logLevel = logLevel;
  }

  public void reset() {
    logLines = null;
    printStream = System.out;
  }

  public void setPrintStream(PrintStream printStream) {
    this.printStream = printStream;
  }

  public void setNullPrintStream() {
    setPrintStream(new PrintStream(nullOutputStream()));
  }

  public void enableLogLines(boolean enable) {
    logLines = enable ? new ArrayList<>() : null;
  }

  public List<String> getLogLines() {
    return logLines;
  }

  private void print(String message) {
    printStream.println(message);
    if (logLines != null) {
      logLines.add(message);
    }
  }

  public void log(Object message) {
    if (canLog()) {
      print(logConfig.messageFormat.buildMessage(logLevel, frameAt(2), Objects.toString(message), null));
    }
  }

  public void log(String message, Object... args) {
    if (canLog()) {
      print(logConfig.messageFormat.buildMessage(logLevel, frameAt(2), format(message, args), null));
    }
  }

  public void log(Throwable throwable) {
    if (canLog()) {
      print(logConfig.messageFormat.buildMessage(logLevel, frameAt(2), "", throwable));
    }
  }

  public void log(Throwable throwable, String message, Object... args) {
    if (canLog()) {
      print(logConfig.messageFormat.buildMessage(logLevel, frameAt(2), format(message, args), throwable));
    }
  }

  private String format(String message, Object... args) {
    return String.format(message, args);
  }

  @VisibleForTesting
  boolean canLog() {
    if (logLevel.code < logConfig.logLevel.code) {
      return false;
    }
    if (logConfig.callersThatLog.isEmpty()) {
      return true;
    }
    return StackTools.stackContainsAnyClass(logConfig.callersThatLog);
  }


}
