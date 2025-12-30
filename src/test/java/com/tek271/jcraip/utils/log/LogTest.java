package com.tek271.jcraip.utils.log;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tek271.jcraip.utils.log.Log.*;
import static org.junit.jupiter.api.Assertions.*;

class LogTest {

  @BeforeEach
  void beforeEach() {
    Log.reset();
  }

  @AfterEach
  void afterEach() {
    Log.reset();
  }

  @Test
  void trace_test() {
    LogConfig.instance.logLevel = LogLevel.TRACE;
    trace.enableLogLines(true);
    trace.setNullPrintStream();
    trace.log("hello");

    String line = trace.getLogLines().getFirst();
    assertTrue(line.startsWith("TRACE "));
    assertTrue(line.endsWith("log.LogTest.trace_test() hello"));
  }

  @Test
  void debug_test() {
    LogConfig.instance.logLevel = LogLevel.DEBUG;
    debug.enableLogLines(true);
    debug.setNullPrintStream();
    debug.log("hello");
    String line = debug.getLogLines().getFirst();
    assertTrue(line.startsWith("DEBUG "));
    assertTrue(line.endsWith("log.LogTest.debug_test() hello"));
  }

  @Test
  void info_test() {
    LogConfig.instance.logLevel = LogLevel.INFO;
    info.enableLogLines(true);
    info.setNullPrintStream();
    info.log("hello");
    String line = info.getLogLines().getFirst();
    assertTrue(line.startsWith("INFO "));
    assertTrue(line.endsWith("log.LogTest.info_test() hello"));
  }

  @Test
  void warn_test() {
    LogConfig.instance.logLevel = LogLevel.WARN;
    warn.enableLogLines(true);
    warn.setNullPrintStream();
    warn.log("hello");
    String line = warn.getLogLines().getFirst();
    assertTrue(line.startsWith("WARN "));
    assertTrue(line.endsWith("log.LogTest.warn_test() hello"));
  }

  @Test
  void error_test() {
    LogConfig.instance.logLevel = LogLevel.ERROR;
    error.enableLogLines(true);
    error.setNullPrintStream();
    error.log("hello");
    String line = error.getLogLines().getFirst();
    assertTrue(line.startsWith("ERROR "));
    assertTrue(line.endsWith("log.LogTest.error_test() hello"));
  }

}