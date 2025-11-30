package com.tek271.jcrap;

import com.tek271.jcrap.utils.CollectionTools;
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
    assertEquals(1, prompt.size());
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
    assertEquals(3, prompt.size());

    CollectionTools.print(prompt, true);
    assertEquals("Given the arguments 1 and 2", prompt.getFirst());
    assertEquals("sum the arguments", prompt.get(1));
    assertEquals("and return the result as int", prompt.get(2));
  }


}