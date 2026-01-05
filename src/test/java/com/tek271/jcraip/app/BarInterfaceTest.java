package com.tek271.jcraip.app;

import com.tek271.jcraip.ai.bogus.BogusCaller;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Test;

import static com.tek271.jcraip.utils.json.PersonForTesting.*;
import static com.tek271.jcraip.utils.json.PersonForTesting.SAM;
import static org.junit.jupiter.api.Assertions.*;

class BarInterfaceTest {
  BogusCaller aiCaller = new BogusCaller();
  BarInterface sut = BarInterface.create(aiCaller, false);

  @Test
  void willRunMethodWithPrompt() {
    aiCaller.ifQthenA("Given the arguments: 13 and 90 and 3, find the maximum", "90");
    int max = sut.max(13, 90, 3);
    assertEquals(90, max);
  }

  @Test
  void noPromptMethod_willFail() {
    NotImplementedException ex = assertThrows(NotImplementedException.class, () -> sut.add_noPrompt(1, 2));
    String expected = "Method BarInterface.add_noPrompt does not have a Prompt annotation or a defined implementation";
    assertEquals(expected, ex.getMessage());
  }

  @Test
  void staticMethod() {
    assertEquals("bar42", BarInterface.staticMethod());
  }

  @Test
  void defaultMethod() {
    assertEquals(10, sut.defaultMethod(5));
  }

  @Test
  void methodWithJsonArg() {
    String jsonText = ADA_SAM_SKY_JSON;

    String question = String.format("""
      Given the arguments:
      as JSON persons=%s
      find the name with highest age
      """, jsonText).trim();
    aiCaller.ifQthenA(question, "Sam");

    String name = sut.findNameOfOldest(jsonText);
    assertEquals(SAM.name(), name);
  }


}