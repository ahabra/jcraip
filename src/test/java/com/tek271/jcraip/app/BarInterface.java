package com.tek271.jcraip.app;

import com.tek271.jcraip.AiObjectFactory;
import com.tek271.jcraip.ai.base.AiCaller;
import com.tek271.jcraip.prompt.Prompt;

public interface BarInterface {

  static BarInterface create(AiCaller aiCaller) {
    return new AiObjectFactory(aiCaller).createProxy(BarInterface.class);
  }

  @Prompt("find the maximum")
  int max(int a, int b, int c);

  int add_noPrompt(int a, int b);

  static String staticMethod() {
    return "bar42";
  }

  default int defaultMethod(int a) {
    return a + a;
  }

}
