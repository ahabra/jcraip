package com.tek271.jcraip;

public interface InterfaceWithSimplePrompt {

  @Prompt("find sum of given numbers")
  int add(int a, int b);

}
