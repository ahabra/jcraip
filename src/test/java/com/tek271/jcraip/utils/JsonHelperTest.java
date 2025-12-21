package com.tek271.jcraip.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonHelperTest {
  public record Person (String name, int age) {}

  @Test
  void toJson_convertsObject() {
    Person person = new Person("John", 25);
    String json = JsonHelper.toJson(person);

    System.out.println(json);
    Person result = JsonHelper.fromJson(Person.class, json);
    assertEquals(person, result);
  }
}