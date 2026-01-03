package com.tek271.jcraip.ai.gemini.structure;

import com.tek271.jcraip.utils.json.Json;

import java.util.Arrays;
import java.util.List;

import static com.tek271.jcraip.utils.json.Json.json;

public record RequestPayload(List<Content> contents) {

  public RequestPayload(Content... contents) {
    this(Arrays.asList(contents));
  }

  /** Create request with a single Content object */
  public RequestPayload(String... texts) {
    this(new Content(texts));
  }

  public String toJson(String indent) {
    return json().indent(indent).toText(this);
  }

  public String toJson() {
    return toJson("");
  }

  public static RequestPayload fromJson(String json) {
    return Json.<RequestPayload>json().baseType(RequestPayload.class).parse(json);
  }

}
