package com.tek271.jcraip.utils.net;

import com.tek271.jcraip.utils.json.PersonForTesting;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

import static com.tek271.jcraip.utils.json.PersonForTesting.SAM;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpCallerIntTest {
  HttpCaller sut = new HttpCaller();

  @Test
  void testGet() {
    HttpResponse<String> response = sut.url("http://example.com")
      .methodGet()
      .send();

    assertEquals(200, response.statusCode());
    assertTrue(response.body().contains("Example Domain"));
  }

  @Test
  void testPost() {
    HttpResponse<String> response = sut.url("https://jsonplaceholder.typicode.com/posts")
      .methodPost()
      .body(SAM.toJson())
      .contentTypeJson()
      .send();
    assertEquals(201, response.statusCode());

    PersonForTesting responsePerson = PersonForTesting.EMPTY.fromJson(response.body());
    assertEquals(SAM, responsePerson);
  }

}