package com.tek271.jcrap.utils;

import com.tek271.jcrap.Prompt;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;

public class ReflectionTools {
  public static ClassLoader getClassLoader() {
    return Thread.currentThread().getContextClassLoader();
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
      Prompt annotation = method.getAnnotation(Prompt.class);
      if (annotation != null) {
        result.add(method);
      }
    }
    return result;
  }

  public static <T> DynamicType.Builder<T> subclass(Class<T> aClass) {
    return new ByteBuddy().subclass(aClass);
  }

  public static <T> T createInstance(DynamicType.Builder<T> subclass) {
    Class<? extends T> cls = subclass.make()
        .load(getClassLoader())
        .getLoaded();
    return newInstance(cls);
  }


}
