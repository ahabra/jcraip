package com.tek271.jpop.app;

import com.tek271.jpop.prompt.Prompt;
import org.apache.commons.lang3.NotImplementedException;

public class FooService {

  @Prompt("Sum them all")
  public int sum(int a, int b, int c, int d) {
    throw new NotImplementedException();
  }

  @Prompt("is it a palindrome")
  public boolean isPalindrome(int x) {
    throw new NotImplementedException();
  }

  @Prompt("find their population standard deviation")
  public double standardDeviation(double a, double b, double c, double d, double e, double f) {
    throw new NotImplementedException();
  }

  public int noPrompt(String value) {
    return value.length();
  }

  // Cannot change static method
  public static int staticMethod() {
    return 42;
  }

}
