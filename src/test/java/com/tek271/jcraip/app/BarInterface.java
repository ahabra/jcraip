package com.tek271.jcraip.app;

import com.tek271.jcraip.AiObjectFactory;
import com.tek271.jcraip.ai.base.AiCaller;
import com.tek271.jcraip.prompt.Prompt;
import com.tek271.jcraip.prompt.PromptRunner;
import com.tek271.jcraip.prompt.PromptRunnerImpl;

public interface BarInterface {

  static BarInterface create(AiCaller aiCaller) {
    PromptRunner promptRunner = new PromptRunnerImpl(aiCaller).logging(true);
    return new AiObjectFactory(promptRunner).createProxy(BarInterface.class);
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
