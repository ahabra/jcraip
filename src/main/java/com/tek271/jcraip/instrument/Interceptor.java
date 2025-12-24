package com.tek271.jcraip.instrument;

import com.tek271.jcraip.prompt.PromptRunner;
import net.bytebuddy.implementation.bind.annotation.*;

import java.lang.reflect.Method;

/**
 * Intercept method calls for bytebuddy
 */
public class Interceptor {

  /**
   * Intercept calls to methods given base type
   */
  public static class TypeInterceptor {
    /**
     * @param proxy The proxy object created by ByteBuddy
     * @param originalMethod The original method that is getting proxied
     * @param superMethod The new proxy method created by ByteBuddy
     * @param args arguments passed to the method
     * @return The result of calling the superMethod or its cached value
     */
    @RuntimeType
    public static Object intercept(@This Object proxy,
                                   @Origin Method originalMethod,
                                   @SuperMethod Method superMethod,
                                   @AllArguments Object[] args) {
      return PromptRunner.runAiMethod(originalMethod, args);
    }
  }


}
