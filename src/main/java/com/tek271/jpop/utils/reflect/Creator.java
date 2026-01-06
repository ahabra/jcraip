package com.tek271.jpop.utils.reflect;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.tek271.jpop.utils.reflect.ReflectionTools.getClassLoader;

public class Creator {

  /** Create an instance of a dynamic type */
  public static <T> T createDynaInstance(DynamicType.Builder<T> subclass) {
    Class<? extends T> cls = createDynaType(subclass);
    return newInstance(cls);
  }

  /** Create a new dynamic type from the given subclass */
  private static <T> Class<? extends T> createDynaType(DynamicType.Builder<T> subclass) {
    try (DynamicType.Unloaded<T> unloaded = subclass.make()) {
      return unloaded
        .load(getClassLoader())
        .getLoaded();
    }
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

  public static <T> DynamicType.Builder<T> subclass(Class<T> aClass) {
    return new ByteBuddy().subclass(aClass);
  }

}
