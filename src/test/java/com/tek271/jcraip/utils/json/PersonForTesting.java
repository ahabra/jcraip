package com.tek271.jcraip.utils.json;

public record PersonForTesting(String name, int age) implements CanJson<PersonForTesting> {

  public static final PersonForTesting EMPTY = new PersonForTesting("", 0);
  public static final PersonForTesting SAM = new PersonForTesting("Sam", 42);
  public static final PersonForTesting ADA = new PersonForTesting("Ada", 10);
  public static final PersonForTesting SKY = new PersonForTesting("Sky", 14);

  public static final String SAM_JSON = """
    {"name":"Sam","age":42}
    """.trim();

  public static final String ADA_SAM_SKY_JSON = """
      [{"name":"Ada","age":10},{"name":"Sam","age":42},{"name":"Sky","age":14}]
    """.trim();



}
