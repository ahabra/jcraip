package com.tek271.jcraip.utils.json;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tek271.jcraip.utils.json.PersonForTesting.*;
import static org.junit.jupiter.api.Assertions.*;

class JsonTest {
  Json sut = new Json();

  @Test
  void canConvertSimpleObjectToText() {
    String text = sut.baseType(PersonForTesting.class).toText(SAM);
    String expected = "{\"name\":\"Sam\",\"age\":42}";
    assertEquals(expected, text);
  }

  @Test
  void canConvertListOfIntegers() {
    List<Integer> list = List.of(1, 2, 3);
    String text = sut.baseType(List.class).parametrizedTypes(Integer.class).toText(list);
    String expected = "[1,2,3]";
    assertEquals(expected, text);
  }

  @Test
  void canConvertListOfObjects() {
    List<PersonForTesting> list = List.of(SAM, ADA, SKY);
    String text = sut.baseType(List.class).parametrizedTypes(PersonForTesting.class).toText(list);

    String expected = """
      [{"name":"Sam","age":42},{"name":"Ada","age":10},{"name":"Sky","age":14}]
      """.trim();
    System.out.println(text);
    assertEquals(expected, text);
  }

  @Test
  void ifBaseTypeWasNotSetThenUseTheObjectType() {
    String text = sut.toText(SAM);
    String expected = "{\"name\":\"Sam\",\"age\":42}";
    assertEquals(expected, text);
  }

}