package com.tek271.jcraip.app;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarInterfaceTest {
  BarInterface sut = BarInterface.create();

  @Disabled
  @Test
  void willRunMethodWithPrompt() {
    int max = sut.max(13, 90, 3);
    assertEquals(90, max);
  }

  @Test
  void noPromptMethod_willFail() {
    NotImplementedException ex = assertThrows(NotImplementedException.class, () -> sut.add_noPrompt(1, 2));
    System.out.println(ex.getMessage());
  }

  @Test
  void staticMethod() {
    assertEquals("bar42", BarInterface.staticMethod());
  }

  @Test
  void defaultMethod() {
    assertEquals(10, sut.defaultMethod(5));
  }

}