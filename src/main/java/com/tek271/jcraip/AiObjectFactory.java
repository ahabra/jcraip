package com.tek271.jcraip;

import com.tek271.jcraip.instrument.Interceptor;
import com.tek271.jcraip.instrument.InterfaceProxy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Method;
import java.util.List;

import static com.tek271.jcraip.utils.reflect.Creator.createDynaInstance;
import static com.tek271.jcraip.utils.reflect.Creator.subclass;
import static com.tek271.jcraip.utils.reflect.ReflectionTools.*;

public class AiObjectFactory {
  InterfaceProxy interfaceProxy =  new InterfaceProxy();

  public <T> T createProxy(Class<T> targetClass) {
    if (targetClass == null) {
      throw new NullPointerException("jcraip cannot proxy a null object");
    }
    if (targetClass.isInterface()) {
      return interfaceProxy.createProxy(targetClass);
    }

    DynamicType.Builder<T> subclass = subclass(targetClass);
    List<Method> methods = findListOfPromptMethods(targetClass);

    for (Method method : methods) {
      subclass = subclass
          .method(ElementMatchers.is(method))
          .intercept(MethodDelegation.to(Interceptor.TypeInterceptor.class));
    }

    return createDynaInstance(subclass);
  }

}
