package com.tek271.jcraip.utils.json;

public record PersonForTesting(String name, int age) implements CanJson<PersonForTesting> {

  public static final PersonForTesting SAM = new PersonForTesting("Sam", 42);
  public static final String SAM_JSON = """
      {"name":"Sam","age":42}
      """.trim();

  public static final PersonForTesting EMPTY = new PersonForTesting("", 0);
}
