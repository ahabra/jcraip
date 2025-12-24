package com.tek271.jcraip.utils.reflect;

import com.tek271.jcraip.prompt.Prompt;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;

public class ReflectionTools {
  public static ClassLoader getClassLoader() {
    return Thread.currentThread().getContextClassLoader();
  }

  public static <T> T createInstance(DynamicType.Builder<T> subclass) {
    Class<? extends T> cls = subclass.make()
      .load(getClassLoader())
      .getLoaded();
    return newInstance(cls);
  }

  public static <T> T newInstance(Class<T> targetClass) {
    Constructor<T> constructor = getConstructor(targetClass);
    return newInstance(constructor);
  }

  private static <T> T newInstance(Constructor<T> constructor) {
    try {
      return constructor.newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      throw new RuntimeException(e);
    }
  }

  private static <T> Constructor<T> getConstructor(Class<T> targetClass) {
    try {
      return targetClass.getDeclaredConstructor();
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
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

  public static <T> DynamicType.Builder<T> subclass(Class<T> aClass) {
    return new ByteBuddy().subclass(aClass);
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
   * Run a method defined as default in an interface, through a dynamic proxy
   * @param proxy
   * @param method
   * @param args
   * @return
   * @param <T>
   */
  @SuppressWarnings("unchecked")
  public static <T> T invokeDefaultMethod(Object proxy, Method method, Object... args) {
    try {
      return (T) InvocationHandler.invokeDefault(proxy, method, args);
    } catch (Throwable e) {
      throw new RuntimeException(e);
    }
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


}
