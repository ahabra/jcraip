package com.tek271.jcraip.app;

import com.tek271.jcraip.AiObjectFactory;
import com.tek271.jcraip.ai.base.AiCaller;
import com.tek271.jcraip.prompt.Prompt;
import com.tek271.jcraip.prompt.PromptRunner;
import com.tek271.jcraip.prompt.PromptRunnerImpl;
import org.apache.commons.lang3.NotImplementedException;

public abstract class AbstractService {

  public static AbstractService createService(AiCaller aiCaller) {
    PromptRunner promptRunner = new PromptRunnerImpl(aiCaller).logging(false);
    return new AiObjectFactory(promptRunner).createProxy(AbstractService.class);
  }

  @Prompt("multiply them")
  public abstract int multiply_abstract(int a, int b);


  @Prompt("multiply them")
  public int multiply_real(int a, int b) {
    throw new NotImplementedException();
  }

}
