package com.tek271.jcraip;

import java.lang.reflect.Method;

public class PromptRunner {

  public static Object run(Method method, Object[] args) {
    PromptBuilder promptBuilder = new PromptBuilder();
    String text = promptBuilder.buildPromptText(method, args);

    // TODO run prompt

    return null;
  }

}
