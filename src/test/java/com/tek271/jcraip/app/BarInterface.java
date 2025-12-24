package com.tek271.jcraip.app;

import com.tek271.jcraip.AiObjectFactory;
import com.tek271.jcraip.prompt.Prompt;

public interface BarInterface {

  static BarInterface create() {
    return new AiObjectFactory().createProxy(BarInterface.class);
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
