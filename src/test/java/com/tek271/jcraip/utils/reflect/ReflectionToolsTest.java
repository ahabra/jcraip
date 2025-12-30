package com.tek271.jcraip.utils.reflect;

import com.tek271.jcraip.prompt.Prompt;
import org.junit.jupiter.api.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.AbstractList;

import static com.tek271.jcraip.utils.reflect.ReflectionTools.*;
import static org.junit.jupiter.api.Assertions.*;

class ReflectionToolsTest {

  @Test
  void isAbstract_test() {
    assertFalse(isAbstract(Object.class));
    assertTrue(isAbstract(AbstractList.class));
  }

  @Test
  void isStatic_test() throws NoSuchMethodException {
    Method staticMethod = System.class.getDeclaredMethod("currentTimeMillis");
    assertTrue(isStatic(staticMethod));

    assertFalse(isStatic(toStringMethod()));
  }

  @Test
  void simpleName_test() {
    assertEquals("Object.toString", ReflectionTools.simpleName(toStringMethod()));
  }

  @Test
  void hasPrompt_test() throws NoSuchMethodException {
    assertFalse(hasPrompt(toStringMethod()));
    Method methodWithPrompt = this.getClass().getDeclaredMethod("methodWithPrompt");
    assertTrue(hasPrompt(methodWithPrompt));
  }

  private static Method toStringMethod() {
    try {
      return Object.class.getDeclaredMethod("toString");
    } catch (NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }

  @Prompt("")
  private void methodWithPrompt() {
  }

  int methodToFind(int a) {
    return a*2;
  }

  @Test
  void findMethod_test() {
    MethodType mt = MethodType.methodType(int.class, int.class);

    MethodHandle mh = findMethod(this.getClass(), "methodToFind", mt);

    assertNotNull(mh);
    MethodType type = mh.type();
    assertEquals(2, type.parameterCount());
    assertEquals(this.getClass(), type.parameterType(0));
    assertEquals(int.class, type.parameterType(1));
    assertEquals(int.class, type.returnType());
  }

}