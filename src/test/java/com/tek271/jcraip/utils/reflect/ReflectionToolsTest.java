package com.tek271.jcraip.utils.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.AbstractList;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionToolsTest {

  @Test
  void isAbstract_test() {
    assertFalse(ReflectionTools.isAbstract(Object.class));
    assertTrue(ReflectionTools.isAbstract(AbstractList.class));
  }

  @Test
  void isStatic_test() throws NoSuchMethodException {
    Method staticMethod = System.class.getDeclaredMethod("currentTimeMillis");
    assertTrue(ReflectionTools.isStatic(staticMethod));

    Method dynaMethod = Object.class.getDeclaredMethod("toString");
    assertFalse(ReflectionTools.isStatic(dynaMethod));
  }

}