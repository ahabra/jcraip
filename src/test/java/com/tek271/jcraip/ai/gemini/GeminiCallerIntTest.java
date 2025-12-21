package com.tek271.jcraip.ai.gemini;

import com.tek271.jcraip.ai.gemini.structure.Answer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeminiCallerIntTest {
  GeminiCaller sut =  new GeminiCaller();

  @Test
  void test1() {
    Answer answer = sut.call("what is the sum of 10 and 20 and return the result as result=");
    assertEquals(200, answer.httpStatus());
    assertEquals("result=30", answer.answer());
  }
}