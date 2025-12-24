package com.tek271.jcraip.utils.reflect;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EvaluatorTest {
  Evaluator sut = new Evaluator();

  @Test
  void testSimpleValues() {
    assertNull(sut.eval(null));
    assertNull(sut.eval("null"));
//    assertEquals("", sut.eval(""));
//    assertEquals("ab", sut.eval("ab"));
  }
}