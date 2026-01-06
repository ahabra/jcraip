package com.tek271.jpop.app;

import com.tek271.jpop.prompt.IsJson;
import com.tek271.jpop.prompt.Prompt;

public interface BarInterface {

  @Prompt("find the maximum")
  int max(int a, int b, int c);

  int add_noPrompt(int a, int b);

  static String staticMethod() {
    return "bar42";
  }

  default int defaultMethod(int a) {
    return a + a;
  }

  @Prompt("find the name with highest age")
  String findNameOfOldest(@IsJson String persons);

  @Prompt("find the person with highest age")
  @IsJson String findOldestPerson(@IsJson String persons);

}
