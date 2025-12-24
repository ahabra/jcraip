package com.tek271.jcraip.app;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("This calls Google Gemini. It will cost $ to run a lot")
class FooServiceTest {
  FooService sut = FooService.create();

  @Test
  void sumFromTo_returnsCorrectValues() {
    assertEquals(15, sut.sumFromTo(1, 5));
  }

  @Test
  void isPalindrome_tes() {
    assertTrue(sut.isPalindrome(12321));
    assertFalse(sut.isPalindrome(1232));
  }

  @Test
  void standardDeviation_test() {
    assertEquals(1.707, sut.standardDeviation(1, 2, 3, 4, 5, 6), 0.001);
  }

  @Test
  void noPrompt_willCallDefinedCode() {
    assertEquals(3, sut.noPrompt("abc"));
  }

  @Test
  void staticMethod_test() {
    assertEquals(42, sut.staticMethod());
  }

}