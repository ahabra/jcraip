package com.tek271.jcraip.utils.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CanJsonTest {

  public record Person (String name, int age) implements CanJson<Person> {}

  private static final Person SAM = new Person("Sam", 42);
  private static final String SAM_JSON = """
      {"name":"Sam","age":42}
      """.trim();


  @Test
  void toJson_noIndent_convertsToJsonString() {
    String json = SAM.toJson();
    assertEquals(SAM_JSON, json);
  }

  @Test
  void toJson_withIndent_convertsToJsonString() {
    String json = SAM.toJson(" ");

    String expected = """
      {
       "name": "Sam",
       "age": 42
      }
      """.trim();
    assertEquals(expected, json);
  }

  @Test
  void fromJson_parsesJsonToObject() {
    Person person = new Person("", 0).fromJson(SAM_JSON);
    assertEquals(SAM, person);
  }
}