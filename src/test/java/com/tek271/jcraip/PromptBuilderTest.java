package com.tek271.jcraip;

import com.tek271.jcraip.prompt.Prompt;
import com.tek271.jcraip.prompt.PromptBuilder;
import com.tek271.jcraip.utils.structures.CollectionTools;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PromptBuilderTest {
  PromptBuilder sut =  new PromptBuilder();

  @Prompt("do something")
  void voidNoArgs() {}

  @Test
  void testVoidNoArgs() throws NoSuchMethodException {
    Method method = this.getClass().getDeclaredMethod("voidNoArgs");
    List<String> prompt = sut.buildPrompt(method);
    assertEquals(2, prompt.size());
    assertEquals("do something", prompt.getFirst());
  }

  @Prompt("sum the arguments")
  int intWithArgs(int a, int b) {
    return 0;
  }

  @Test
  void testIntWithArgs() throws NoSuchMethodException {
    Method method = this.getClass().getDeclaredMethod("intWithArgs", int.class, int.class);
    List<String> prompt = sut.buildPrompt(method, 1, 2);
    assertEquals(5, prompt.size());

    assertEquals("Given the arguments:", prompt.getFirst());
    assertEquals("a=1", prompt.get(1));
    assertEquals("and b=2", prompt.get(2));
    assertEquals("sum the arguments", prompt.get(3));
    assertEquals("and return the result as result=", prompt.get(4));
  }


}