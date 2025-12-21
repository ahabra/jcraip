package com.tek271.jcraip.utils.net;

import com.tek271.jcraip.utils.JsonHelper;
import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpCallerIntTest {
  HttpCaller sut = new HttpCaller();

  public record Person (String name, int age) {

    String toJson() {
      return JsonHelper.toJson(this);
    }

    static Person fromJson(String json) {
      return JsonHelper.fromJson(Person.class, json);
    }
  }

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
    Person person = new Person("John", 25);

    HttpResponse<String> response = sut.url("https://jsonplaceholder.typicode.com/posts")
      .methodPost()
      .body(person.toJson())
      .contentTypeJson()
      .send();
    assertEquals(201, response.statusCode());

    Person responsePerson = Person.fromJson(response.body());
    assertEquals(person, responsePerson);
  }

}