# Java Prompt Oriented Programming (JPOP)
Started on Fri Nov 28 20:28:18 EST 2025

## Under Construction

## Prompt Oriented Programming (POP)
POP is a new term (as of January 2026) which means using AI/LLM prompts to execute programming tasks.
Some simple examples:
1. If you have two numbers where you want to find which one is greater, you can just pass these numbers
   in a prompt to an AI and ask it to find the maximum.
2. If you want to find the sum of a sequence of numbers, you can pass these numbers in a prompt to an AI and ask it
   to find their sum.
3. If you have a list of names, and you want to find the ones that end with the letter _z_, you can pass these names
   in a prompt to an AI and ask it to find the ones ending with letter _z_.

### POP Advantages
1. Simplify some of the tedious programming tasks
2. In a future POP programming environment, we can think of programs as pure natural languages prompts, where the AI/LLM
   is the execution module
3. Prompts do not have to be English-based, they can be in any language which the AI understands

### POP Disadvantages
1. Executing a prompt could be orders of magnitude slower than running a custom programs to do the same tasks
2. Executing a prompt on a not-free AI can become expensive quickly
3. Passing data in a prompt to an external AI can expose private data to outside inspection

### My Own Views
As of now (January 2026), POP is not ready for any production usage, I initially thought of it as a joke.
However, if you think of cloud computing or video streaming in the mid 1990s, they had a lot of _disadvantages_,
but we use them now globally.

With optimized AI/LLMs, POP could become as widley used as cloud computing or video streaming.

## Introduction to JPOP
`JPOP` is a Java library that supports Prompt Oriented Programming (POP) using annotations.
For example, let's define an interface named `BarInterface`:

```java
interface Bar {
  @Prompt("find the maximum")
  int max(int a, int b, int c);
}
```

To use this interface:

```java
class BarTest {

  @Test
  void willRunMethodWithPrompt() {
    // Somehow create a POP aware Bar instance
    // Will be explained later
    Bar bar = createPopAwareInstance();

    int max = bar.max(13, 90, 3);
    assertEquals(90, max);
  }
}
```

Observe how we do not provide any source for the method `Bar.max()`,
we only provide a prompt using the `@Prompt` annotation.



## TODOs
1. consider using interface instead of solid class. DONE.
2. allow json-able arguments. DONE.
3. allow json-able method return value. DONE.
4. refactor test services to mock `PromptRunner`. DONE.
5. cleanup logging. DONE.
6. try using a local AI