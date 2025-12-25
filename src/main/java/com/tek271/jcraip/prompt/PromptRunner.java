package com.tek271.jcraip.prompt;

import java.lang.reflect.Method;

public interface PromptRunner {
  Object run(Method method, Object[] args);
}
