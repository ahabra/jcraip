package com.tek271.jcraip.instrument;

import com.tek271.jcraip.prompt.PromptRunner;
import com.tek271.jcraip.utils.reflect.ReflectionTools;
import org.apache.commons.lang3.NotImplementedException;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InterfaceProxy {

  @SuppressWarnings("unchecked")
  public <T> T createProxy(Class<T> targetInterface) {
    ClassLoader classLoader = ReflectionTools.getClassLoader();
    Class<?>[] interfaces = {targetInterface};
    return (T) Proxy.newProxyInstance(classLoader, interfaces, this::invoke);
  }

  Object invoke(Object proxy, Method method, Object[] args) {
    if (!ReflectionTools.hasPrompt(method)) {
      return methodWithNoPrompt(proxy, method, args);
    }
    return PromptRunner.runAiMethod(method, args);
  }

  private Object methodWithNoPrompt(Object proxy, Method method, Object[] args) {
    if (ReflectionTools.isStatic(method)) {
      return ReflectionTools.invokeMethod(null, method, args);
    }
    if (method.isDefault()) {
      return ReflectionTools.invokeDefaultMethod(proxy, method, args);
    }

    throw new NotImplementedException("Method " + method.getName() + " does not have a Prompt annotation");
  }


}
