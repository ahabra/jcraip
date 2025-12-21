package com.tek271.jcraip.ai.gemini.structure;

import com.tek271.jcraip.utils.json.JsonHelper;

import java.util.Arrays;
import java.util.List;

public record RequestPayload(List<Content> contents) {

  public RequestPayload(Content... contents) {
    this(Arrays.asList(contents));
  }

  /** Create request with a single Content object */
  public RequestPayload(String... texts) {
    this(new Content(texts));
  }

  public String toJson(String indent) {
    return JsonHelper.toJson(this, indent);
  }

  public String toJson() {
    return toJson("");
  }

  public static RequestPayload fromJson(String json) {
    return JsonHelper.fromJson(RequestPayload.class, json);
  }

}
