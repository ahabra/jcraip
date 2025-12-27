package com.tek271.jcraip.app;

import com.tek271.jcraip.ai.base.AiCaller;
import com.tek271.jcraip.ai.gemini.GeminiCaller;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("running this often will cost $")
class BarInterfaceIntTest {
  AiCaller aiCaller = new GeminiCaller();
  BarInterface sut = BarInterface.create(aiCaller, true);

  @Test
  void willRunMethodWithPrompt() {
    int max = sut.max(13, 90, 3);
    assertEquals(90, max);
  }

}