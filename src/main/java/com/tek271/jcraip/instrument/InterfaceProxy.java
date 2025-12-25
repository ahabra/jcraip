package com.tek271.jcraip.instrument;

import com.tek271.jcraip.prompt.PromptRunner;
import com.tek271.jcraip.utils.reflect.ReflectionTools;
import org.apache.commons.lang3.NotImplementedException;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/** Proxy for interfaces */
public class InterfaceProxy {
  private final PromptRunner promptRunner;

  public InterfaceProxy(PromptRunner promptRunner) {
    this.promptRunner = promptRunner;
  }

  @SuppressWarnings("unchecked")
  public <T> T createProxy(Class<T> targetInterface) {
    ClassLoader classLoader = ReflectionTools.getClassLoader();
    Class<?>[] interfaces = {targetInterface};
    return (T) Proxy.newProxyInstance(classLoader, interfaces, this::invoke);
  }

  private Object invoke(Object proxy, Method method, Object[] args) {
    if (!ReflectionTools.hasPrompt(method)) {
      return methodWithNoPrompt(proxy, method, args);
    }
    return promptRunner.run(method, args);
  }

  private Object methodWithNoPrompt(Object proxy, Method method, Object[] args) {
    if (ReflectionTools.isStatic(method)) {
      return ReflectionTools.invokeMethod(null, method, args);
    }
    if (method.isDefault()) {
      return ReflectionTools.invokeDefaultMethod(proxy, method, args);
    }

    String err = """
      Method %s does not have a Prompt annotation or a defined implementation
      """.formatted(ReflectionTools.simpleName(method));
    throw new NotImplementedException(err);
  }

}
