package com.tek271.jcraip;

import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.reflect.Method;
import java.util.List;

import static com.tek271.jcraip.utils.ReflectionTools.*;

public class AiObjectFactory {

  public static <T> T createProxy(Class<T> targetClass) {
    if (targetClass == null) {
      throw new NullPointerException("jcraip cannot proxy a null object");
    }
    List<Method> methods = findListOfPromptMethods(targetClass);
    if (methods.isEmpty()) {
      // TODO is this really good
      return null;
    }
    DynamicType.Builder<T> subclass = subclass(targetClass);

    for (Method method : methods) {
      subclass = subclass
          .method(ElementMatchers.is(method))
          .intercept(MethodDelegation.to(Interceptor.TypeInterceptor.class));
    }

    return createInstance(subclass);
  }

}
