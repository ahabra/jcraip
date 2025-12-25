package com.tek271.jcraip;

import com.tek271.jcraip.instrument.Interceptor;
import com.tek271.jcraip.instrument.InterfaceProxy;
import com.tek271.jcraip.prompt.PromptRunnerImpl;
import com.tek271.jcraip.prompt.PromptRunner;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Method;
import java.util.List;

import static com.tek271.jcraip.utils.reflect.Creator.createDynaInstance;
import static com.tek271.jcraip.utils.reflect.Creator.subclass;
import static com.tek271.jcraip.utils.reflect.ReflectionTools.*;

public class AiObjectFactory {
  private final InterfaceProxy interfaceProxy =  new InterfaceProxy();
  private final Interceptor interceptor = new Interceptor();

  public <T> T createProxy(Class<T> targetClass, PromptRunner promptRunner) {
    if (targetClass == null) {
      throw new NullPointerException("jcraip cannot proxy a null object");
    }
    setPromptRunner(promptRunner);
    if (targetClass.isInterface()) {
      return interfaceProxy.createProxy(targetClass);
    }

    DynamicType.Builder<T> subclass = subclass(targetClass);
    List<Method> methods = findListOfPromptMethods(targetClass);

    for (Method method : methods) {
      subclass = subclass
          .method(ElementMatchers.is(method))
          .intercept(MethodDelegation.to(interceptor));
    }

    return createDynaInstance(subclass);
  }

  public <T> T createProxy(Class<T> targetClass) {
    return createProxy(targetClass, null);
  }

  private void setPromptRunner(PromptRunner promptRunner) {
    if (promptRunner == null) {
      promptRunner = new PromptRunnerImpl();
    }
    interceptor.setPromptRunner(promptRunner);
    interfaceProxy.setPromptRunner(promptRunner);
  }

}
