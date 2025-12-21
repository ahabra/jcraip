package com.tek271.jcraip.ai.gemini.structure;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

  static Part createPart(int code) {
    return new Part("part-" + code);
  }

  static Content createContent(int code) {
    List<Part> parts = List.of(createPart(code), createPart(code+1));
    return new Content("role-" + code, parts);
  }

  static Request createRequest() {
    List<Content> contents = List.of(createContent(1), createContent(10));
    return new Request(contents);
  }

  @Test
  void toJson_test() {
    Request request = createRequest();
    String json = request.toJson();
    Request result = Request.fromJson(json);
    assertEquals(request, result);
  }
}