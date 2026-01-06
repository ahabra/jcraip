package com.tek271.jpop.utils.json;

import org.junit.jupiter.api.Test;

import static com.tek271.jpop.utils.json.PersonForTesting.SAM;
import static com.tek271.jpop.utils.json.PersonForTesting.SAM_JSON;
import static org.junit.jupiter.api.Assertions.*;

class CanJsonTest {

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
    PersonForTesting person = PersonForTesting.EMPTY.fromJson(SAM_JSON);
    assertEquals(SAM, person);
  }

}