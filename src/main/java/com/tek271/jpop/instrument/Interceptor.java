package com.tek271.jpop.instrument;

import com.tek271.jpop.prompt.PromptRunner;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;

/**
 * Intercept method calls for bytebuddy
 */
public class Interceptor {
  private final PromptRunner promptRunner;

  public Interceptor(PromptRunner promptRunner) {
    this.promptRunner = promptRunner;
  }

  /**
   * @param originalMethod The original method that is getting proxied
   * @param args           arguments passed to the method
   * @return The result of calling the AI engine to solve the method's specs
   */
  @RuntimeType
  public Object intercept(@Origin Method originalMethod,
                          @AllArguments Object[] args) {
//    System.out.println(originalMethod);
    return promptRunner.run(originalMethod, args);
  }


}
