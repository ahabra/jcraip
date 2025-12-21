package com.tek271.jcraip.ai.gemini.structure;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestPayloadTest {

  static Part createPart(int code) {
    return new Part("part-" + code);
  }

  static Content createContent(int code) {
    List<Part> parts = List.of(createPart(code), createPart(code+1));
    return new Content("role-" + code, parts);
  }

  static RequestPayload createRequest() {
    List<Content> contents = List.of(createContent(1), createContent(10));
    return new RequestPayload(contents);
  }

  @Test
  void toJson_test() {
    RequestPayload requestPayload = createRequest();
    String json = requestPayload.toJson();
    RequestPayload result = RequestPayload.fromJson(json);
    assertEquals(requestPayload, result);
  }

  @Test
  void constructor_test() {
    RequestPayload requestPayload = new RequestPayload("a", "b");
    String json = requestPayload.toJson("  ");

    RequestPayload result = RequestPayload.fromJson(json);
    assertEquals(requestPayload, result);
  }
}