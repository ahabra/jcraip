package com.tek271.jcraip.app;

import com.tek271.jcraip.ai.bogus.BogusCaller;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FooServiceTest {
  BogusCaller aiCaller = new BogusCaller();
  FooService sut = FooService.create(aiCaller);

  @Test
  void sum_returnsCorrectValues() {
    aiCaller.ifQthenA("Given the arguments 1 and 4 and 10 and 20 Sum them all", "35");
    assertEquals(35, sut.sum(1, 4, 10, 20));
  }

  @Test
  void isPalindrome_test() {
    aiCaller.ifQthenA("Given the arguments 12321 is it a palindrome", "true");
    aiCaller.ifQthenA("Given the arguments 1232 is it a palindrome", "false");
    assertTrue(sut.isPalindrome(12321));
    assertFalse(sut.isPalindrome(1232));
  }

  @Test
  void standardDeviation_test() {
    aiCaller.ifQthenA("Given the arguments 1.0 and 2.0 and 3.0 and 4.0 and 5.0 and 6.0 find their population standard deviation", "1.707");
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