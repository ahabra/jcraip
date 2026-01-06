package com.tek271.jpop.app;

import com.tek271.jpop.AiObjectFactory;
import com.tek271.jpop.ai.base.AiCaller;
import com.tek271.jpop.prompt.Prompt;
import com.tek271.jpop.prompt.PromptRunner;
import com.tek271.jpop.prompt.PromptRunnerImpl;
import org.apache.commons.lang3.NotImplementedException;

public abstract class AbstractService {

  public static AbstractService createService(AiCaller aiCaller, boolean isLogging) {
    PromptRunner promptRunner = new PromptRunnerImpl(aiCaller).logging(isLogging);
    return new AiObjectFactory(promptRunner).createProxy(AbstractService.class);
  }

  @Prompt("multiply them")
  public abstract int multiply_abstract(int a, int b);


  @Prompt("multiply them")
  public int multiply_real(int a, int b) {
    throw new NotImplementedException();
  }

}
