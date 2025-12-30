package com.tek271.jcraip.utils.log.format;

import com.google.common.base.Splitter;
import com.tek271.jcraip.utils.reflect.StackTools;
import org.junit.jupiter.api.Test;

import java.lang.StackWalker.StackFrame;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.tek271.jcraip.utils.log.LogLevel.*;
import static com.tek271.jcraip.utils.log.format.MessageFormat.DATE_FORMAT_DEFAULT;
import static com.tek271.jcraip.utils.log.format.MessageFormat.DEFAULT_FORMAT;
import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.*;

class MessageFormatTest {
  MessageFormat sut = new MessageFormat();

  @Test
  void simpleTest() {
    StackFrame frame = StackTools.currentLocation();
    sut = sut.level().space().date().space()
      .className().placeholder(".").method().placeholder("() ")
      .message();
    String msg = sut.buildMessage(DEBUG, frame, "foo", null);

    String date = now().format(DateTimeFormatter.ofPattern(DATE_FORMAT_DEFAULT));
    String expected = "DEBUG " + date + " format.MessageFormatTest.simpleTest() foo";

    assertEquals(expected, msg);
  }

  @Test
  void classNameDepth_test() {
    StackFrame frame = StackTools.currentLocation();

    String msg = sut.className().buildMessage(DEBUG, frame, "foo", null);
    assertEquals("format.MessageFormatTest", msg);

    msg = sut.clear().className(1).buildMessage(DEBUG, frame, "foo", null);
    assertEquals("format.MessageFormatTest", msg);

    msg = sut.clear().className(2).buildMessage(DEBUG, frame, "foo", null);
    assertEquals("log.format.MessageFormatTest", msg);

    msg = sut.clear().className(3).buildMessage(DEBUG, frame, "foo", null);
    assertEquals("utils.log.format.MessageFormatTest", msg);

    msg = sut.clear().className(4).buildMessage(DEBUG, frame, "foo", null);
    assertEquals("jcraip.utils.log.format.MessageFormatTest", msg);

    msg = sut.clear().className(5).buildMessage(DEBUG, frame, "foo", null);
    assertEquals("tek271.jcraip.utils.log.format.MessageFormatTest", msg);

    msg = sut.clear().className(6).buildMessage(DEBUG, frame, "foo", null);
    assertEquals("com.tek271.jcraip.utils.log.format.MessageFormatTest", msg);

    msg = sut.clear().className(10).buildMessage(DEBUG, frame, "foo", null);
    assertEquals("com.tek271.jcraip.utils.log.format.MessageFormatTest", msg);

    msg = sut.clear().className(1000).buildMessage(DEBUG, frame, "foo", null);
    assertEquals("com.tek271.jcraip.utils.log.format.MessageFormatTest", msg);
  }

  @Test
  void throwable_test() {
    sut = sut.clear().level().space().throwable();
    Exception ex = new RuntimeException("foo");
    String msg = sut.buildMessage(ERROR, null, null, ex);

    List<String> lines = Splitter.on("\n").trimResults().splitToList(msg);
    assertEquals(MessageFormat.STACK_DEPTH_DEFAULT + 2, lines.size());
    assertEquals("ERROR", lines.get(0));
    assertEquals("java.lang.RuntimeException: foo", lines.get(1));
    assertTrue(lines.get(2).startsWith("com.tek271.jcraip.utils.log.format.MessageFormatTest.throwable_test"));
  }

  @Test
  void cannotAddAfterLocking() {
    sut = sut.level().lock();

    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> sut.space());
    assertEquals(MessageFormat.LOCK_ERR, exception.getMessage());
  }

  @Test
  void defaultFormat_withException() {
    sut = DEFAULT_FORMAT;
    StackFrame frame = StackTools.currentLocation();
    Exception ex = new RuntimeException("foo");

    String msg = sut.buildMessage(INFO, frame, "msg1", ex);

    List<String> lines = Splitter.on("\n").trimResults().splitToList(msg);
    assertTrue(lines.get(0).startsWith("INFO "));
    assertTrue(lines.get(0).endsWith("format.MessageFormatTest.defaultFormat_withException() msg1"));
    assertEquals("java.lang.RuntimeException: foo", lines.get(1));
  }

  @Test
  void defaultFormat_withNoException() {
    sut = DEFAULT_FORMAT;
    StackFrame frame = StackTools.currentLocation();

    String msg = sut.buildMessage(TRACE, frame, "msg1", null);
    assertTrue(msg.startsWith("TRACE "));
    assertTrue(msg.endsWith("format.MessageFormatTest.defaultFormat_withNoException() msg1"));
  }

}