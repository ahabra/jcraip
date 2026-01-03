package com.tek271.jcraip.utils.json;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import static com.tek271.jcraip.utils.json.JsonHelper.moshi;
import static com.tek271.jcraip.utils.json.JsonHelper.moshiAdapter;
import static com.tek271.jcraip.utils.json.PersonForTesting.SAM;
import static org.junit.jupiter.api.Assertions.*;

class JsonHelperTest {

  @Test
  void toJson_convertsObject() {
    String json = JsonHelper.toJson(SAM);

    PersonForTesting result = JsonHelper.fromJson(PersonForTesting.class, json);
    assertEquals(SAM, result);
  }

  @Test
  void toJson_convertList1() {
    List<Integer> list = List.of(1, 2, 3);

    Type type = Types.newParameterizedType(List.class, Integer.class);
    JsonAdapter<List<Integer>> jsonAdapter = moshi().adapter(type);
    String json = jsonAdapter.toJson(list);
    System.out.println(json);
  }

  @Test
  void toJson_convertList2() throws IOException {
    List<Integer> list = List.of(1, 2, 3);

    JsonAdapter<List> adapter = moshiAdapter(List.class, Integer.class);
    String json = adapter.toJson(list);

    System.out.println(json);

    adapter.fromJson(json);
  }

  @Test
  void test3() throws IOException {
    JsonAdapter<PersonForTesting> adapter = moshiAdapter(PersonForTesting.class);
    String json = adapter.toJson(SAM);
    PersonForTesting parsed = adapter.fromJson(json);
    assertEquals(SAM, parsed);
  }



}