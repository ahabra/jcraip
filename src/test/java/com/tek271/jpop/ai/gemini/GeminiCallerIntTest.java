package com.tek271.jpop.ai.gemini;

import com.tek271.jpop.ai.base.AiAnswer;
import com.tek271.jpop.ai.base.AiQuestion;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.tek271.jpop.ai.gemini.GeminiCaller.getGeminiApiKey;
import static org.junit.jupiter.api.Assertions.*;

class GeminiCallerIntTest {
  GeminiCaller sut =  new GeminiCaller();

  @Test
  @Disabled("We do not want to run this all the time")
  void callingGeminiWithSimpleMathQuestion() {
    AiQuestion aiQuestion = new AiQuestion("what is the sum of 10 and 20 and return the result as result=");
    AiAnswer answer = sut.call(aiQuestion);
    assertEquals(200, answer.responseCode());
    assertEquals("result=30", answer.text());
  }

  @Test
  void getGeminiApiKey_failsIfEnvVarIsNotDefined() {
    String keyName = "DOES_NOT_EXIST_API_KEY____";

    IllegalStateException exception = assertThrows(IllegalStateException.class, () -> getGeminiApiKey(keyName));
    assertTrue(exception.getMessage().startsWith(keyName));

  }
}