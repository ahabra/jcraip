package com.tek271.jcraip.utils.log;

import com.google.common.base.Splitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogWriterTest {
  private static final LogLevel LOG_LEVEL = LogLevel.INFO;
  LogWriter sut;

  @BeforeEach
  void beforeEach() {
    sut = new LogWriter(LOG_LEVEL);
    sut.logLines = new ArrayList<>();
    sut.setPrintStream( new PrintStream(OutputStream.nullOutputStream()) );
  }

  @Test
  void log_message() {
    sut.log("m1");
    String line = sut.logLines.getFirst();
    assertTrue(line.startsWith(LOG_LEVEL.toString()));
    assertTrue(line.endsWith("log.LogWriterTest.log_message() m1"));
  }

  @Test
  void log_messageWithArgs() {
    sut.log("name=%s, age=%s", "bob", 42);
    String line = sut.logLines.getFirst();
    assertTrue(line.endsWith("log_messageWithArgs() name=bob, age=42"));
  }

  @Test
  void log_throwable() {
    Exception ex = new IllegalStateException();
    sut.log(ex);
    List<String> lines = Splitter.on('\n')
      .trimResults().omitEmptyStrings()
      .splitToList(sut.logLines.getFirst());
    assertTrue(lines.getFirst().endsWith("log.LogWriterTest.log_throwable()"));
    assertEquals(ex.toString(), lines.get(1));
  }

  @Test
  void log_messageWithArgsAndThrowable() {
    Exception ex = new IllegalStateException();
    sut.log(ex, "name=%s, age=%s", "bob", 42);
    List<String> lines = Splitter.on('\n')
      .trimResults().omitEmptyStrings()
      .splitToList(sut.logLines.getFirst());
    assertTrue(lines.getFirst().endsWith("log_messageWithArgsAndThrowable() name=bob, age=42"));
    assertEquals(ex.toString(), lines.get(1));
  }

  // TODO test if logging level is too low to log
  // TODO test control with logConfig.callersThatLog

}