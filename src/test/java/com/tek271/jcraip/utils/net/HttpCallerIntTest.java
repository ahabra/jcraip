package com.tek271.jcraip.utils.net;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpCallerIntTest {
  HttpCaller sut = new HttpCaller();

  public record Person (String name, int age) {

    private static JsonAdapter<Person>  jsonAdapter() {
      Moshi moshi = new Moshi.Builder().build();
      return moshi.adapter(Person.class);
    }

    String toJson() {
      return jsonAdapter().toJson(this);
    }

    static Person fromJson(String json) {
      try {
        return jsonAdapter().fromJson(json);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
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