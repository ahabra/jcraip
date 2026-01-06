package com.tek271.jpop.app;

import com.tek271.jpop.ai.base.AiCaller;
import com.tek271.jpop.ai.gemini.GeminiCaller;
import com.tek271.jpop.utils.json.PersonForTesting;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.tek271.jpop.utils.json.PersonForTesting.*;
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

  @Test
  void methodWithJsonArg() {
    String name = sut.findNameOfOldest(ADA_SAM_SKY_JSON);
    assertEquals(SAM.name(), name);
  }

  @Test
  void methodWithJsonReturn() {
    String json = sut.findOldestPerson(ADA_SAM_SKY_JSON);

    PersonForTesting found = EMPTY.fromJson(json);
    assertEquals(SAM, found);
  }

}