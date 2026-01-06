package com.tek271.jpop.utils.reflect;

import java.lang.StackWalker.StackFrame;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Stack trace tools
 */
public class StackTools {

  /**
   * Get the top of the stack frames
   * @param depth number of frames from the top
   * @return List of frames
   */
  public static List<StackFrame> stackTop(int depth) {
    return walker().walk(s -> s.limit(depth).collect(Collectors.toList()));
  }

  private static StackWalker walker() {
    return StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
  }

  /**
   * Get the stack frame at the given index
   * @param index index of the stack frame
   * @return the found frame, or null if not found
   */
  public static StackFrame frameAt(int index) {
    Optional<StackFrame> optional = walker().walk(s -> s.skip(index).findFirst());
    return optional.orElse(null);
  }

  /** Get the StackFrame at the currently executing method */
  public static StackFrame currentLocation() {
    return frameAt(2);
  }

  /**
   * Get the current executing method
   * @param objAtLocation You must pass <code>new Object() {}</code>
   * @return the currently executing method
   */
  public static Method currentMethod(Object objAtLocation) {
    return objAtLocation.getClass().getEnclosingMethod();
  }

  /**
   * Check if the stack contains any of the given classes
   * @param classSet a set of classes
   * @return true if classSet is empty, or if any of the classes is found. false otherwise.
   */
  public static boolean stackContainsAnyClass(Set<Class<?>> classSet) {
    if (classSet == null || classSet.isEmpty()) {
      return true;
    }
    return walker().walk(s -> s.map(StackFrame::getDeclaringClass)
      .anyMatch(classSet::contains));
  }

  /**
   * Check if the stack contains any of the given classes
   * @param classes a vararg array of classes
   * @return true if classes is empty, or if any of the classes is found. false otherwise.
   */
  public static boolean stackContainsAnyClass(Class<?>... classes) {
    if (classes.length == 0) {
      return true;
    }
    return stackContainsAnyClass(Set.of(classes));
  }

  /** Helps with debugging */
  public static void printStackFrame(StackFrame frame, String title) {
    System.out.println(title);
    System.out.println("  DeclaringClass: " + frame.getDeclaringClass());
    System.out.println("  className: " + frame.getClassName());
    System.out.println("  methodName: " + frame.getMethodName());
    System.out.println("  methodType: " + frame.getMethodType());
    System.out.println("  descriptor: " + frame.getDescriptor());
    System.out.println("  fileName: " + frame.getFileName());
    System.out.println("  lineNumber: " + frame.getLineNumber());
  }

}
