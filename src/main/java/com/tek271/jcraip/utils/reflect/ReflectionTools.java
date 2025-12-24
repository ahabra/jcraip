package com.tek271.jcraip.utils.reflect;

import com.tek271.jcraip.prompt.Prompt;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class ReflectionTools {
  public static ClassLoader getClassLoader() {
    return Thread.currentThread().getContextClassLoader();
  }

  public static boolean isAbstract(Class<?> aClass) {
    return Modifier.isAbstract(aClass.getModifiers());
  }

  public static boolean isStatic(Method method) {
    return Modifier.isStatic(method.getModifiers());
  }

  public static boolean hasPrompt(Method method) {
    return method.isAnnotationPresent(Prompt.class);
  }

  public static String simpleName(Method method) {
    return method.getDeclaringClass().getSimpleName() + "." + method.getName();
  }


  public static List<Method> findListOfPromptMethods(Class<?> targetClass) {
    Method[] methods = targetClass.getDeclaredMethods();
    List<Method> result = new ArrayList<>();
    for (Method method : methods) {
      if (hasPrompt(method)) {
        result.add(method);
      }
    }
    return result;
  }

  @SuppressWarnings("unchecked")
  public static <T> T invokeMethod(Object obj, Method method, Object... args) {
    try {
      return (T) method.invoke(obj, args);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Invoke a method defined as default in an interface, through a dynamic proxy
   */
  @SuppressWarnings("unchecked")
  public static <T> T invokeDefaultMethod(Object proxy, Method method, Object... args) {
    try {
      return (T) InvocationHandler.invokeDefault(proxy, method, args);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
  }

}
