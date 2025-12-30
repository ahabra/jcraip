package com.tek271.jcraip.utils.log;

import com.google.common.base.Splitter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tek271.jcraip.utils.log.LogLevel.INFO;
import static com.tek271.jcraip.utils.log.LogLevel.WARN;
import static org.junit.jupiter.api.Assertions.*;

class LogWriterTest {
  private static final LogLevel LOG_LEVEL = INFO;
  LogWriter sut;

  @BeforeEach
  void beforeEach() {
    LogConfig.instance.reset();
    sut = new LogWriter(LOG_LEVEL);
    sut.enableLogLines(true);
    sut.setNullPrintStream();
  }

  @Test
  void log_message() {
    sut.log("m1");
    String line = sut.getLogLines().getFirst();
    assertTrue(line.startsWith(LOG_LEVEL.toString()));
    assertTrue(line.endsWith("log.LogWriterTest.log_message() m1"));
  }

  @Test
  void log_messageWithArgs() {
    sut.log("name=%s, age=%s", "bob", 42);
    String line = sut.getLogLines().getFirst();
    assertTrue(line.endsWith("log_messageWithArgs() name=bob, age=42"));
  }

  @Test
  void log_throwable() {
    Exception ex = new IllegalStateException();
    sut.log(ex);
    List<String> lines = Splitter.on('\n')
      .trimResults().omitEmptyStrings()
      .splitToList(sut.getLogLines().getFirst());
    assertTrue(lines.getFirst().endsWith("log.LogWriterTest.log_throwable()"));
    assertEquals(ex.toString(), lines.get(1));
  }

  @Test
  void log_messageWithArgsAndThrowable() {
    Exception ex = new IllegalStateException();
    sut.log(ex, "name=%s, age=%s", "bob", 42);
    List<String> lines = Splitter.on('\n')
      .trimResults().omitEmptyStrings()
      .splitToList(sut.getLogLines().getFirst());
    assertTrue(lines.getFirst().endsWith("log_messageWithArgsAndThrowable() name=bob, age=42"));
    assertEquals(ex.toString(), lines.get(1));
  }

  @Test
  void willNotLogIfLogLevelIsLessThanConfigLevel() {
    LogConfig.instance.logLevel = WARN;
    sut.log("m1");
    assertEquals(0, sut.getLogLines().size());
  }

  @Test
  void willNotLogIfCallersThatLogContainUnusedClasses() {
    LogConfig.instance.callersThatLog.add(String.class);
    sut.log("m1");
    assertEquals(0, sut.getLogLines().size());
  }

  @Test
  void willLogIfCallersThatLogContainUsedClasses() {
    LogConfig.instance.callersThatLog.add(String.class);
    LogConfig.instance.callersThatLog.add(this.getClass());
    sut.log("m1");
    assertEquals(1, sut.getLogLines().size());
    String line = sut.getLogLines().getFirst();
    assertTrue(line.endsWith("willLogIfCallersThatLogContainUsedClasses() m1"));
  }

  @Test
  void canLog_returnsFalseIfLogLevelIsLow() {
    LogConfig.instance.logLevel = WARN;
    sut =  new LogWriter(INFO);
    assertFalse(sut.canLog());
  }

  @Test
  void canLog_returnsTrueIfCallsThatLogIsEmpty() {
    LogConfig.instance.callersThatLog.clear();
    assertTrue(sut.canLog());
  }

  @Test
  void canLog_returnsFalseIfCallsThatLogContainUnusedClasses() {
    LogConfig.instance.callersThatLog.add(String.class);
    assertFalse(sut.canLog());
  }

  @Test
  void canLog_returnsTrueIfCallsThatLogContainUsedClasses() {
    LogConfig.instance.callersThatLog.add(this.getClass());
    assertTrue(sut.canLog());
  }

}