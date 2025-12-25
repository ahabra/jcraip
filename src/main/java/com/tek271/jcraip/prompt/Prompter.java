package com.tek271.jcraip.prompt;

import java.lang.reflect.Method;

public interface Prompter {
  Object run(Method method, Object[] args);
}
