package com.tek271.jcraip.utils.reflect;

import org.junit.jupiter.api.Test;

import java.lang.StackWalker.StackFrame;
import java.lang.reflect.Method;
import java.util.List;

import static com.tek271.jcraip.utils.reflect.StackTools.*;
import static org.junit.jupiter.api.Assertions.*;

class StackToolsTest {

  @Test
  void stackTop_test() {
    List<StackFrame> frames = stackTop(3);
    assertEquals(3, frames.size());

    assertEquals(StackTools.class, frames.get(0).getDeclaringClass());
    assertEquals(this.getClass(), frames.get(1).getDeclaringClass());
  }

  @Test
  void frameAt_test() {
    StackFrame stackFrame = frameAt(0);
    assertEquals(StackTools.class, stackFrame.getDeclaringClass());
    assertEquals("frameAt", stackFrame.getMethodName());

    stackFrame = frameAt(1);
    assertEquals(this.getClass(), stackFrame.getDeclaringClass());
    assertEquals("frameAt_test", stackFrame.getMethodName());

    stackFrame = frameAt(10000);
    assertNull(stackFrame);
  }

  @Test
  void currentLocation_test() {
    StackFrame location = currentLocation();
    assertEquals(this.getClass(), location.getDeclaringClass());
    assertEquals("currentLocation_test", location.getMethodName());
  }

  @Test
  void currentMethod_test() {
    Method method = currentMethod(new Object() {});

    assertEquals(this.getClass(), method.getDeclaringClass());
    assertEquals("currentMethod_test", method.getName());
  }

  @Test
  void stackContainsAnyClass_test() {
    assertTrue(stackContainsAnyClass());
    assertTrue(stackContainsAnyClass(StackTools.class));
    assertTrue(stackContainsAnyClass(this.getClass()));
    assertTrue(stackContainsAnyClass(this.getClass(), StackTools.class));
    assertTrue(stackContainsAnyClass(int.class, StackTools.class));
    assertFalse(stackContainsAnyClass(int.class));
  }

}