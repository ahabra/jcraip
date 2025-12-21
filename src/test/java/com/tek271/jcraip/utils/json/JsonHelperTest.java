package com.tek271.jcraip.utils.json;

import org.junit.jupiter.api.Test;

import static com.tek271.jcraip.utils.json.PersonForTesting.SAM;
import static org.junit.jupiter.api.Assertions.*;

class JsonHelperTest {

  @Test
  void toJson_convertsObject() {
    String json = JsonHelper.toJson(SAM);

    System.out.println(json);
    PersonForTesting result = JsonHelper.fromJson(PersonForTesting.class, json);
    assertEquals(SAM, result);
  }
}