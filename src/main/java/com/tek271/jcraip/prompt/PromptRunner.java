package com.tek271.jcraip.prompt;

import java.lang.reflect.Method;

public interface PromptRunner {
  Object run(Method method, Object[] args);

  PromptRunner logging(boolean isLog);

  default PromptRunner logging() {
    return logging(true);
  }

  boolean isLogging();
}
