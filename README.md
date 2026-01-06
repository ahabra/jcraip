# Java Prompt Oriented Programming (JPOP)
Started on Fri Nov 28 20:28:18 EST 2025

## Under Construction

## Introduction
Use AI prompts instead of code to implement methods. For example, let's define an interface named `BarInterface`:

```java
interface BarInterface {
  @Prompt("find the maximum")
  int max(int a, int b, int c);
}
```

To use this interface

```java
class BarInterfaceIntTest {

  @Test
  void willRunMethodWithPrompt() {
    // Note how an instance of BarInterface is created
    // The code will utilize Google Gemini
    BarInterface bar = AiObjectBuilder.aiBuilder()
      .aiCaller(new GeminiCaller())
      .createProxy(BarInterface.class);

    int max = bar.max(13, 90, 3);
    assertEquals(90, max);
  }
}
```

Observe how we do not provide any source for the method `BarInterface.max()`,
we only provide a prompt using the `@Prompt` annotation.



## TODOs
1. consider using interface instead of solid class. DONE.
2. allow json-able arguments. DONE.
3. allow json-able method return value. DONE.
4. refactor test services to mock `PromptRunner`. DONE.
5. cleanup logging. DONE.
6. try using a local AI