package com.tek271.jpop;

import com.tek271.jpop.ai.base.AiCaller;
import com.tek271.jpop.prompt.PromptRunner;
import com.tek271.jpop.prompt.PromptRunnerImpl;

public class AiObjectBuilder {
  private AiCaller aiCaller;
  private boolean isLogging = false;

  private AiObjectBuilder() {}

  public static AiObjectBuilder aiBuilder() {
    return new AiObjectBuilder();
  }

  public AiObjectBuilder isLogging(boolean isLogging) {
    this.isLogging = isLogging;
    return this;
  }

  public AiObjectBuilder aiCaller(AiCaller aiCaller) {
    this.aiCaller = aiCaller;
    return this;
  }

  public <T> T createProxy(Class<T> targetClass) {
    PromptRunner promptRunner = new PromptRunnerImpl(aiCaller).logging(isLogging);
    AiObjectFactory aiObjectFactory = new AiObjectFactory(promptRunner);
    return aiObjectFactory.createProxy(targetClass);
  }

}
