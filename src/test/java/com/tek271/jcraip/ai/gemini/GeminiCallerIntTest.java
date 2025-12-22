package com.tek271.jcraip.ai.gemini;

import com.tek271.jcraip.ai.gemini.structure.Answer;
import org.junit.jupiter.api.Test;

import static com.tek271.jcraip.ai.gemini.GeminiCaller.getGeminiApiKey;
import static org.junit.jupiter.api.Assertions.*;

class GeminiCallerIntTest {
  GeminiCaller sut =  new GeminiCaller();

  @Test
  void callingGeminiWithSimpleMathQuestion() {
    Answer answer = sut.call("what is the sum of 10 and 20 and return the result as result=");
    assertEquals(200, answer.httpStatus());
    assertEquals("result=30", answer.answer());
  }

  @Test
  void getGeminiApiKey_failsIfEnvVarIsNotDefined() {
    String keyName = "DOES_NOT_EXIST_API_KEY____";

    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> getGeminiApiKey(keyName));
    assertTrue(exception.getMessage().startsWith(keyName));

  }
}