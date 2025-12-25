package com.tek271.jcraip.instrument;

import com.tek271.jcraip.prompt.PromptRunner;
import com.tek271.jcraip.prompt.Prompter;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;

/**
 * Intercept method calls for bytebuddy
 */
public class Interceptor {
  private Prompter prompter = new PromptRunner();

  /**
   * @param originalMethod The original method that is getting proxied
   * @param args           arguments passed to the method
   * @return The result of calling the AI engine to solve the method's specs
   */
  @RuntimeType
  public Object intercept(@Origin Method originalMethod,
                          @AllArguments Object[] args) {
    System.out.println(originalMethod);
    return prompter.run(originalMethod, args);
  }

  public void setPrompter(Prompter prompter) {
    this.prompter = prompter;
  }


}
