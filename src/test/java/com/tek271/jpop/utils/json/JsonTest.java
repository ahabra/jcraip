package com.tek271.jpop.utils.json;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tek271.jpop.utils.json.PersonForTesting.*;
import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

  @Test
  void canConvertSimpleObjectToText() {
    Json<PersonForTesting> sut = new Json<>();
    String text = sut.baseType(PersonForTesting.class).toText(SAM);
    assertEquals(SAM.toJson(), text);

    PersonForTesting parsed = sut.parse(text);
    assertEquals(SAM, parsed);
  }

  @Test
  void canConvertListOfIntegers() {
    Json<List> sut = new Json<>();
    List<Integer> list = List.of(1, 2, 3);
    String text = sut.baseType(List.class).parametrizedTypes(Integer.class).toText(list);
    String expected = "[1,2,3]";
    assertEquals(expected, text);

    List<Integer> parsed = sut.parse(text);
    assertEquals(list, parsed);
  }

  @Test
  void canConvertListOfObjects() {
    Json<List> sut = new Json<>();
    List<PersonForTesting> list = List.of(ADA, SAM, SKY);
    String text = sut.baseType(List.class).parametrizedTypes(PersonForTesting.class).toText(list);
    assertEquals(ADA_SAM_SKY_JSON, text);

    List<PersonForTesting> parsed = sut.parse(text);
    assertEquals(list, parsed);
  }

  @Test
  void ifBaseTypeWasNotSetThenUseTheObjectType() {
    Json<PersonForTesting> sut = new Json<>();
    String text = sut.toText(SAM);
    assertEquals(SAM.toJson(), text);

    PersonForTesting parsed = sut.parse(text);
    assertEquals(SAM, parsed);
  }


}