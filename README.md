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

You may think of a POP task as a function that expects some input arguments and return some output, the
body of the function is a prompt.

### POP Advantages
1. Simplify some of the tedious programming tasks
2. In a future POP programming environment, we can think of programs as pure natural languages prompts, where the AI/LLM
   is the execution module
3. Prompts do not have to be English-based, they can be in any language which the AI understands

### POP Disadvantages
1. Executing a prompt could be orders of magnitude slower than running a custom program to do the same tasks
2. Executing a prompt on a not-free AI can become expensive quickly
3. Passing data in a prompt to an external AI can expose private data to outside inspection

### My Own Views
As of now (January 2026), POP is not ready for any production usage, I initially thought of it as a joke.
However, if you think of cloud computing or video streaming in the mid 1990s, they had similar _disadvantages_,
but we use them now globally.

With optimized AI/LLMs, POP could become as widley used as cloud computing or video streaming.

## Introduction to JPOP
`JPOP` is a Java library that supports Prompt Oriented Programming (POP) using annotations.
For example, let's define an interface named `Bar`:

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


## Setup

### 1. Get the Library
Add the following dependency to your maven's `pom.xml`:

```xml
    <dependency>
      <groupId>com.tek271</groupId>
      <artifactId>jpop</artifactId>
      <!-- FIXME put the right version -->
      <version>???</version>
    </dependency>
```

### 2. Set Environment Variable
JPOP uses Google's Gemini for running prompts. You must have a Google Gemini account and obtain a Google Gemini API key.
This API key is private, do not share it with others, and **do not add it to your git repo**.

Set an environment variable named `GEMINI_API_KEY` with its value as your API key.
In a unix-like environment, you can do:

```shell
export GEMINI_API_KEY=<your-private-gemini-api-key>
```

## Usage
Let's define a body-less method in an interface. The method will have a `Prompt` annotation
that explains to the AI, the task which the method should perform

```java
import com.tek271.jpop.prompt.Prompt;

interface Bar {
  @Prompt("find the maximum")
  int max(int a, int b, int c);
}
```

Now, we need to create an instance of `Bar` that uses Gemini for finding the maximum:

_Note: the following code is not complete, it is meant to show the main concept_.

```java
import com.tek271.jpop.AiObjectBuilder;
import com.tek271.jpop.ai.gemini.GeminiCaller;

// Create a Bar instance utilizing Gemini
Bar bar = AiObjectBuilder.aiBuilder()
  .aiCaller(new GeminiCaller())
  .createProxy(Bar.class);

System.out.println(bar.max(13, 200, 5));  // will print 200
```

## Programmer's Reference
You can use the `Prompt` annotation with any of the following type of methods:

1. In an interface, on any body-less method. You cannot use it with `default` or `static` methods.
2. In an abstract class, on both `abstract` methods, and implemented non-static methods.
3. In a regular class, any non-static method.

To create an instance of the class that uses the `Prompt` annotation, you use the `AiObjectBuilder` class:

```java
import com.tek271.jpop.AiObjectBuilder;
import com.tek271.jpop.ai.gemini.GeminiCaller;


AiObjectBuilder.aiBuilder()
  .isLogging(true)     // will log the full prompt and response
  .aiCaller(new GeminiCaller())   // Currently Google Gemini is the only supported AI
  .createProxy(SomeClassWithPromptAnnotatedMethods.class);  // create an object of the given class
```

### Arguments and Return Types
The `Prompt` annotated method's argument and return type ust be one of the following:

1. A primitive type: boolean, int, float, ...
2. A wrapper type: Boolean, Integer, Float, ...
3. String

Additionally, a method can have `String` type arguments as JSON strings, or return a JSON string.
To indicate that an argument or return value is a JSON string, use the `IsJson` annotation.

An example for a method that takes a JSON argument:

```java
import com.tek271.jpop.prompt.IsJson;
import com.tek271.jpop.prompt.Prompt;


  @Prompt("find the name with highest age")
  String findNameOfOldest(@IsJson String persons);
```

In the above example, if we pass the following JSON string:

```json
[
  { "name": "Ada", "age": 10 },
  { "name": "Sam", "age": 42 },
  { "name": "Sky", "age": 14 }
]
```
The method will return `Sam`.

An example for a method that takes a JSON argument, and return a JSON string:

```java
  @Prompt("find the person with highest age")
  @IsJson String findOldestPerson(@IsJson String persons);
```

_Note how the method's return type is annotated with `IsJson`._

If we pass the same JSON string, the method will return the following JSON:

```json
  { "name": "Sam", "age": 42 }
```



## TODOs
1. consider using interface instead of solid class. DONE.
2. allow json-able arguments. DONE.
3. allow json-able method return value. DONE.
4. refactor test services to mock `PromptRunner`. DONE.
5. cleanup logging. DONE.
6. try using a local AI
7. Enhance README